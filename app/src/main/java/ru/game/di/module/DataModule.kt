package ru.game.di.module

import dagger.Module
import dagger.Provides
import ru.game.brain.data.repository.GameRepositoryImpl
import ru.game.brain.domain.repository.GameRepository

@Module
class DataModule {

    @Provides
    fun provideGameRepository(): GameRepository = GameRepositoryImpl

}