package ru.game.domain.model

data class GameResultDomain(
    val isWin: Boolean,
    val countOfRightAnswer: Int,
    val countOfQuestions: Int,
    val gameSettings: GameSettings
)