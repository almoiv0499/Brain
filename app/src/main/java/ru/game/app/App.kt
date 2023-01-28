package ru.game.app

import android.app.Application
import ru.game.di.component.AppComponent
import ru.game.di.component.DaggerAppComponent

class App : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.factory().create(this)
    }

}