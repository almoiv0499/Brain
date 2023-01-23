package ru.game.brain.presentation.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.game.brain.domain.model.Level
import ru.game.brain.domain.usecase.GenerateQuestionUseCase
import ru.game.brain.domain.usecase.GetGameSettingsUseCase
import ru.game.brain.presentation.viewmodel.GameViewModel

class GameViewModelFactory(
    private val generateQuestionUseCase: GenerateQuestionUseCase,
    private val getGameSettingsUseCase: GetGameSettingsUseCase,
    private val application: Application,
    private val level: Level
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GameViewModel(
            generateQuestionUseCase = generateQuestionUseCase,
            getGameSettingsUseCase = getGameSettingsUseCase,
            application = application,
            level = level
        ) as T
    }
}