package ru.game.domain.usecase

import ru.game.domain.model.GameSettings
import ru.game.domain.model.LevelDomain
import ru.game.domain.repository.GameRepository

class GetGameSettingsUseCase(
    private val repository: GameRepository
) {

    operator fun invoke(level: LevelDomain): GameSettings =
        repository.getGameSettings(level)

}