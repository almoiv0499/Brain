package ru.game.brain.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.game.brain.domain.model.Question
import ru.game.brain.domain.repository.GameRepository
import javax.inject.Inject

class GenerateQuestionUseCase @Inject constructor(
    private val repository: GameRepository,
    private val ioDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(maxSumValue: Int): Question = withContext(ioDispatcher) {
        repository.generateQuestion(maxSumValue, COUNT_OF_QUESTIONS)
    }

    private companion object {
        const val COUNT_OF_QUESTIONS = 6
    }


}

