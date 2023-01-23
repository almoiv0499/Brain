package ru.game.domain.repository

import ru.game.domain.model.GameSettings
import ru.game.domain.model.LevelDomain
import ru.game.domain.model.Question

interface GameRepository {

    fun generateQuestion(maxSumValue: Int, countOfQuestion: Int): Question

    fun getGameSettings(level: LevelDomain): GameSettings
}