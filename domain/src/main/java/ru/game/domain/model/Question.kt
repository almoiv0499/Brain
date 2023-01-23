package ru.game.domain.model

data class Question(
    val sum: Int,
    val visibleNumber: Int,
    val options: List<Int>
) {
    val rightAnswer: Int
        get() = sum - visibleNumber
}

