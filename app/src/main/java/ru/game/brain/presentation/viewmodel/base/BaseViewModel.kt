package ru.game.brain.presentation.viewmodel.base

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import ru.game.brain.presentation.fragment.util.Event
import ru.game.brain.presentation.fragment.util.NavigationCommand

open class BaseViewModel : ViewModel() {

    private val _navigateLiveData = MutableLiveData<Event<NavigationCommand>>()
    val navigationLiveData: LiveData<Event<NavigationCommand>> = _navigateLiveData

    private val _exceptionLiveData = MutableLiveData<Event<Int>>()
    val exceptionLiveData: LiveData<Event<Int>> = _exceptionLiveData

    fun navigate(navDirections: NavDirections) {
        _navigateLiveData.value = Event(NavigationCommand.NavigationTo(navDirections))
    }

    fun navigateBack() {
        _navigateLiveData.value = Event(NavigationCommand.Back)
    }

    fun exceptionMessage(@StringRes message: Int) {
        _exceptionLiveData.value = Event(message)
    }

}