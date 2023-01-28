package ru.game.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.game.brain.presentation.viewmodel.GameViewModel
import ru.game.di.annotation.ViewModelKey

@Module
interface GameViewModelModule {

    @Binds
    @[IntoMap ViewModelKey(GameViewModel::class)]
    fun bindGameViewModel(viewModel: GameViewModel): ViewModel

}