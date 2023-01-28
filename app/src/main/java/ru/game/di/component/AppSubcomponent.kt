package ru.game.di.component

import dagger.BindsInstance
import dagger.Subcomponent
import ru.game.brain.domain.model.Level
import ru.game.brain.presentation.fragment.GameFragment
import ru.game.di.annotation.QualifierLevel
import ru.game.di.module.GameViewModelModule

@Subcomponent(modules = [GameViewModelModule::class])
interface AppSubcomponent {

    fun inject(fragment: GameFragment)

    @Subcomponent.Factory
    interface SubcomponentFactory {
        fun create(@BindsInstance @QualifierLevel level: Level): AppSubcomponent
    }

}