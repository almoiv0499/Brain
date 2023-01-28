package ru.game.brain.domain.repository

import ru.game.brain.domain.model.GameSettings
import ru.game.brain.domain.model.Level
import ru.game.brain.domain.model.Question

interface GameRepository {

    suspend fun generateQuestion(maxSumValue: Int, countOfQuestion: Int): Question

    fun getGameSettings(level: Level): GameSettings
}