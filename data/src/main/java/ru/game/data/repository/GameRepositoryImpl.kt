package ru.game.data.repository

import ru.game.data.mapper.mapToGameSettingsDomain
import ru.game.data.mapper.mapToQuestionDomain
import ru.game.data.model.GameSettings
import ru.game.data.model.Question
import ru.game.domain.model.GameSettings
import ru.game.domain.model.LevelDomain
import ru.game.domain.model.Question
import ru.game.domain.repository.GameRepository
import java.lang.Integer.max
import kotlin.math.min
import kotlin.random.Random

object GameRepositoryImpl : GameRepository {

    private const val MIN_SUM = 2
    private const val MIN_ANSWER = 1

    override fun generateQuestion(maxSumValue: Int, countOfQuestion: Int): ru.game.domain.model.Question {
        val sum = Random.nextInt(MIN_SUM, maxSumValue + 1)
        val visibleNumber = Random.nextInt(MIN_ANSWER, sum)
        val options = HashSet<Int>()
        val rightAnswer = sum - visibleNumber
        options.add(rightAnswer)
        val from = max(rightAnswer - countOfQuestion, MIN_ANSWER)
        val to = min(maxSumValue, rightAnswer + countOfQuestion)
        while (options.size < countOfQuestion) {
            options.add(Random.nextInt(from, to))
        }
        return Question(sum, visibleNumber, options.toList()).mapToQuestionDomain()
    }

    override fun getGameSettings(level: LevelDomain): ru.game.domain.model.GameSettings = when (level) {
        LevelDomain.TEST -> {
            GameSettings(
                maxSumValue = 10,
                minCountOfRightAnswer = 7,
                minPercentOfRightAnswer = 80,
                gameTimeInSecond = 10
            ).mapToGameSettingsDomain()
        }

        LevelDomain.LOW -> {
            GameSettings(
                maxSumValue = 50,
                minCountOfRightAnswer = 10,
                minPercentOfRightAnswer = 65,
                gameTimeInSecond = 25
            ).mapToGameSettingsDomain()
        }

        LevelDomain.MIDDLE -> {
            GameSettings(
                maxSumValue = 100,
                minCountOfRightAnswer = 16,
                minPercentOfRightAnswer = 80,
                gameTimeInSecond = 30
            ).mapToGameSettingsDomain()
        }

        LevelDomain.HIGH -> {
            GameSettings(
                maxSumValue = 1000,
                minCountOfRightAnswer = 20,
                minPercentOfRightAnswer = 85,
                gameTimeInSecond = 60
            ).mapToGameSettingsDomain()
        }
    }
}