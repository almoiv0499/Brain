package ru.game.brain.domain.usecase

import ru.game.brain.domain.model.GameSettings
import ru.game.brain.domain.model.Level
import ru.game.brain.domain.repository.GameRepository

class GetGameSettingsUseCase(
    private val repository: GameRepository
) {

    operator fun invoke(level: Level): GameSettings =
        repository.getGameSettings(level)

}