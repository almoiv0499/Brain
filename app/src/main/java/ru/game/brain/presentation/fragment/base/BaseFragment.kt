package ru.game.brain.presentation.fragment.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.game.brain.presentation.fragment.util.NavigationCommand
import ru.game.brain.presentation.viewmodel.base.BaseViewModel

abstract class BaseFragment<VM : BaseViewModel> : Fragment() {

    abstract val viewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeNavigation()
        observeException()
    }

    private fun observeException() {
        viewModel.exceptionLiveData.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let { message ->
                Toast.makeText(requireActivity(), message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun observeNavigation() {
        viewModel.navigationLiveData.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let { navigationCommand ->
                handleNavigation(navigationCommand)
            }
        }
    }

    private fun handleNavigation(navigationCommand: NavigationCommand) {
        when (navigationCommand) {
            is NavigationCommand.NavigationTo -> {
                findNavController().navigate(navigationCommand.directions)
            }
            is NavigationCommand.Back -> {
                findNavController().navigateUp()
            }
        }
    }

}