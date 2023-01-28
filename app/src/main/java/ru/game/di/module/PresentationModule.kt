package ru.game.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.game.brain.presentation.viewmodel.ChooseLevelViewModel
import ru.game.brain.presentation.viewmodel.GameFinishedViewModel
import ru.game.brain.presentation.viewmodel.WelcomeViewModel
import ru.game.di.annotation.ViewModelKey

@Module
interface PresentationModule {

    @Binds
    @[IntoMap ViewModelKey(WelcomeViewModel::class)]
    fun bindWelcomeViewModel(viewModel: WelcomeViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(ChooseLevelViewModel::class)]
    fun bindChooseLevelViewModel(viewModel: ChooseLevelViewModel): ViewModel

//    @Binds
//    @[IntoMap ViewModelKey(GameViewModel::class)]
//    fun bindGameViewModel(viewModel: GameViewModel): BaseViewModel

    @Binds
    @[IntoMap ViewModelKey(GameFinishedViewModel::class)]
    fun bindGameFinishedViewModel(viewModel: GameFinishedViewModel): ViewModel

}