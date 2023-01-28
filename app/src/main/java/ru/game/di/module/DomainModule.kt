package ru.game.di.module

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import ru.game.brain.domain.repository.GameRepository
import ru.game.brain.domain.usecase.GenerateQuestionUseCase
import ru.game.brain.domain.usecase.GetGameSettingsUseCase

@Module
class DomainModule {

    @Provides
    fun provideDispatcherIO(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideGenerateQuestionUseCase(
        repository: GameRepository,
        ioDispatcher: CoroutineDispatcher
    ): GenerateQuestionUseCase = GenerateQuestionUseCase(
        repository = repository, ioDispatcher = ioDispatcher
    )

    @Provides
    fun provideGetGameSettingUseCase(
        repository: GameRepository
    ): GetGameSettingsUseCase = GetGameSettingsUseCase(repository = repository)

}