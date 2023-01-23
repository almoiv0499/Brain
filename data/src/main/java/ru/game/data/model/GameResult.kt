package ru.game.data.model

data class GameResult(
    val isWin: Boolean,
    val countOfRightAnswer: Int,
    val countOfQuestions: Int,
    val gameSettings: GameSettings
)