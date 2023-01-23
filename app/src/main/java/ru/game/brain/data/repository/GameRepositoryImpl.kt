package ru.game.brain.data.repository

import ru.game.brain.domain.model.GameSettings
import ru.game.brain.domain.model.Level
import ru.game.brain.domain.model.Question
import ru.game.brain.domain.repository.GameRepository
import java.lang.Integer.max
import kotlin.math.min
import kotlin.random.Random

object GameRepositoryImpl : GameRepository {

    private const val MIN_SUM = 2
    private const val MIN_ANSWER = 1

    override fun generateQuestion(maxSumValue: Int, countOfQuestion: Int): Question {
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
        return Question(sum, visibleNumber, options.toList())
    }

    override fun getGameSettings(level: Level): GameSettings = when (level) {
        Level.TEST -> {
            GameSettings(
                maxSumValue = 10,
                minCountOfRightAnswer = 7,
                minPercentOfRightAnswer = 80,
                gameTimeInSecond = 10
            )
        }

        Level.LOW -> {
            GameSettings(
                maxSumValue = 50,
                minCountOfRightAnswer = 10,
                minPercentOfRightAnswer = 65,
                gameTimeInSecond = 25
            )
        }

        Level.MIDDLE -> {
            GameSettings(
                maxSumValue = 100,
                minCountOfRightAnswer = 16,
                minPercentOfRightAnswer = 80,
                gameTimeInSecond = 30
            )
        }

        Level.HIGH -> {
            GameSettings(
                maxSumValue = 1000,
                minCountOfRightAnswer = 20,
                minPercentOfRightAnswer = 85,
                gameTimeInSecond = 60
            )
        }
    }
}