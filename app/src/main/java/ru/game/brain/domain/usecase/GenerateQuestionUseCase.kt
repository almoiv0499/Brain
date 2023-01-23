package ru.game.brain.domain.usecase

import ru.game.brain.domain.model.Question
import ru.game.brain.domain.repository.GameRepository

class GenerateQuestionUseCase(
    private val repository: GameRepository
) {

    operator fun invoke(maxSumValue: Int): Question =
        repository.generateQuestion(maxSumValue, COUNT_OF_QUESTIONS)

    private companion object {
        const val COUNT_OF_QUESTIONS = 6
    }


}