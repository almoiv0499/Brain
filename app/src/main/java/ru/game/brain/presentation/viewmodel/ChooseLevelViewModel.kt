package ru.game.brain.presentation.viewmodel

import ru.game.brain.domain.model.Level
import ru.game.brain.presentation.fragment.ChooseLevelFragmentDirections
import ru.game.brain.presentation.viewmodel.base.BaseViewModel
import javax.inject.Inject

class ChooseLevelViewModel @Inject constructor() : BaseViewModel() {

    fun navigateToGameFragment(level: Level) {
        navigate(ChooseLevelFragmentDirections.actionChooseLevelFragmentToGameFragment(level))
    }

}