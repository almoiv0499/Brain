package ru.game.di.component

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.game.brain.presentation.fragment.ChooseLevelFragment
import ru.game.brain.presentation.fragment.GameFinishedFragment
import ru.game.brain.presentation.fragment.WelcomeFragment
import ru.game.di.annotation.AppScope
import ru.game.di.module.DataModule
import ru.game.di.module.DomainModule
import ru.game.di.module.PresentationModule

@AppScope
@Component(modules = [DomainModule::class, DataModule::class, PresentationModule::class])
interface AppComponent {

    fun appSubcomponentFactory(): AppSubcomponent.SubcomponentFactory

    fun inject(fragment: WelcomeFragment)

    fun inject(fragment: ChooseLevelFragment)

    fun inject(fragment: GameFinishedFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }

}