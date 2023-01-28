package ru.game.brain.presentation.viewmodel

import ru.game.brain.presentation.viewmodel.base.BaseViewModel
import javax.inject.Inject

class GameFinishedViewModel @Inject constructor() : BaseViewModel() {

    fun navigateToChooseLevelFragment() {
        navigateBack()
    }

}