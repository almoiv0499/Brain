package ru.game.brain.presentation.fragment.util

import androidx.navigation.NavDirections

sealed class NavigationCommand {
    data class NavigationTo(val directions: NavDirections): NavigationCommand()
    object Back : NavigationCommand()
}