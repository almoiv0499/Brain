package ru.game.brain.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ru.game.app.App
import ru.game.brain.databinding.FragmentChooseLevelBinding
import ru.game.brain.domain.model.Level
import ru.game.brain.presentation.fragment.base.BaseFragment
import ru.game.brain.presentation.viewmodel.ChooseLevelViewModel
import ru.game.brain.presentation.viewmodelfactory.GameViewModelFactory
import ru.game.di.component.DaggerAppComponent
import javax.inject.Inject

class ChooseLevelFragment : BaseFragment<ChooseLevelViewModel>() {

    private lateinit var binding: FragmentChooseLevelBinding

    @Inject
    lateinit var viewModelFactory: GameViewModelFactory

    override val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(requireActivity(), viewModelFactory)[ChooseLevelViewModel::class.java]
    }

    private val component by lazy(LazyThreadSafetyMode.NONE) {
        (requireActivity().applicationContext as App).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChooseLevelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            buttonLevelTest.setOnClickListener {
                navigateToGameFragment(Level.TEST)
            }
            buttonLevelEasy.setOnClickListener {
                navigateToGameFragment(Level.LOW)
            }
            buttonLevelMiddle.setOnClickListener {
                navigateToGameFragment(Level.MIDDLE)
            }
            buttonLevelHard.setOnClickListener {
                navigateToGameFragment(Level.HIGH)
            }
        }
    }

    private fun navigateToGameFragment(level: Level) {
        viewModel.navigateToGameFragment(level)
    }
}