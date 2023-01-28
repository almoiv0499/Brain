package ru.game.brain.presentation.viewmodel

import ru.game.brain.presentation.fragment.WelcomeFragmentDirections
import ru.game.brain.presentation.viewmodel.base.BaseViewModel
import javax.inject.Inject

class WelcomeViewModel @Inject constructor() : BaseViewModel() {

    fun navigateToChooseLevelFragment() {
        navigate(WelcomeFragmentDirections.actionWelcomeFragmentToChooseLevelFragment())
    }

}