package ru.game.brain.presentation.viewmodel

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.game.brain.R
import ru.game.brain.domain.model.GameResult
import ru.game.brain.domain.model.GameSettings
import ru.game.brain.domain.model.Level
import ru.game.brain.domain.model.Question
import ru.game.brain.domain.usecase.GenerateQuestionUseCase
import ru.game.brain.domain.usecase.GetGameSettingsUseCase

class GameViewModel(
    private val generateQuestionUseCase: GenerateQuestionUseCase,
    private val getGameSettingsUseCase: GetGameSettingsUseCase,
    private val application: Application,
    private val level: Level
) : ViewModel() {

    private lateinit var gameSettingsView: GameSettings
    private var timer: CountDownTimer? = null

    private var countRightAnswers = 0
    private var countQuestions = 0

    private val _liveDataQuestion = MutableLiveData<Question>()
    val liveDataQuestion: LiveData<Question> = _liveDataQuestion

    private val _liveDataTimer = MutableLiveData<String>()
    val liveDataTimer: LiveData<String> = _liveDataTimer

    private val _liveDataPercentRightAnswers = MutableLiveData<Int>()
    val liveDataPercentRightAnswers: LiveData<Int> = _liveDataPercentRightAnswers

    private val _liveDataProgressAnswers = MutableLiveData<String>()
    val liveDataProgressAnswers: LiveData<String> = _liveDataProgressAnswers

    private val _liveDataEnoughCountRightAnswers = MutableLiveData<Boolean>()
    val liveDataEnoughCountRightAnswers: LiveData<Boolean> = _liveDataEnoughCountRightAnswers

    private val _liveDataEnoughPercentRightAnswers = MutableLiveData<Boolean>()
    val liveDataEnoughPercentRightAnswers: LiveData<Boolean> = _liveDataEnoughPercentRightAnswers

    private val _liveDataMinPercent = MutableLiveData<Int>()
    val liveDataMinPercent: LiveData<Int> = _liveDataMinPercent

    private val _liveDataGameResult = MutableLiveData<GameResult>()
    val liveDataGameResult: LiveData<GameResult> = _liveDataGameResult

    init {
        startGame()
    }

    private fun startGame() {
        getGameSettings()
        startTimer()
        generateQuestion()
        updateProgress()
    }

    fun chooseAnswer(number: Int) {
        checkAnswer(number)
        updateProgress()
        generateQuestion()
    }

    private fun updateProgress() {
        val percent = calculatePercentOfRightAnswers()
        _liveDataPercentRightAnswers.value = percent
        _liveDataProgressAnswers.value = String.format(
            application.resources.getString(R.string.right_answers),
            countRightAnswers,
            gameSettingsView.minCountOfRightAnswer
        )
        _liveDataEnoughCountRightAnswers.value =
            countRightAnswers >= gameSettingsView.minCountOfRightAnswer
        _liveDataEnoughPercentRightAnswers.value =
            percent >= gameSettingsView.minPercentOfRightAnswer
    }

    private fun calculatePercentOfRightAnswers(): Int {
        if (countQuestions == 0) return 0
        return (countRightAnswers / countQuestions.toDouble() * 100).toInt()
    }

    private fun checkAnswer(number: Int) {
        val rightAnswer = liveDataQuestion.value?.rightAnswer
        if (number == rightAnswer) {
            countRightAnswers++
        }
        countQuestions++
    }

    private fun generateQuestion() {
        _liveDataQuestion.value =
            generateQuestionUseCase(gameSettingsView.maxSumValue)
    }

    private fun getGameSettings() {
        this.gameSettingsView = getGameSettingsUseCase(level)
        _liveDataMinPercent.value = gameSettingsView.minPercentOfRightAnswer
    }


    private fun startTimer() {
        timer = object : CountDownTimer(
            gameSettingsView.gameTimeInSecond * MILLIS_IN_SECONDS,
            MILLIS_IN_SECONDS
        ) {
            override fun onTick(millis: Long) {
                _liveDataTimer.value = timer(millis)
            }

            override fun onFinish() {
                finishGame()
            }
        }
        timer?.start()
    }

    private fun timer(millis: Long): String {
        val seconds = millis / MILLIS_IN_SECONDS
        val minutes = seconds / SECONDS_IN_MINUTES
        val leftSeconds = seconds - (minutes * SECONDS_IN_MINUTES)
        return String.format("%02d:%02d", minutes, leftSeconds)
    }

    private fun finishGame() {
        _liveDataGameResult.value = GameResult(
            isWin = _liveDataEnoughCountRightAnswers.value == true
                    && _liveDataEnoughPercentRightAnswers.value == true,
            countOfRightAnswer = countRightAnswers,
            countOfQuestions = countQuestions,
            gameSettings = gameSettingsView
        )
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }

    companion object {
        private const val MILLIS_IN_SECONDS = 1000L
        private const val SECONDS_IN_MINUTES = 60
    }

}