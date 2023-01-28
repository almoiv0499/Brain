package ru.game.brain.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import ru.game.app.App
import ru.game.brain.databinding.FragmentGameFinishedBinding
import ru.game.brain.presentation.fragment.base.BaseFragment
import ru.game.brain.presentation.viewmodel.GameFinishedViewModel
import ru.game.brain.presentation.viewmodelfactory.GameViewModelFactory
import ru.game.di.component.DaggerAppComponent
import javax.inject.Inject

class GameFinishedFragment : BaseFragment<GameFinishedViewModel>() {

    private lateinit var binding: FragmentGameFinishedBinding

    @Inject
    lateinit var viewModelFactory: GameViewModelFactory

    override val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(requireActivity(), viewModelFactory)[GameFinishedViewModel::class.java]
    }

    private val component by lazy(LazyThreadSafetyMode.NONE) {
        (requireActivity().applicationContext as App).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    private val args by navArgs<GameFinishedFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.gameResult = args.gameResults
        binding.buttonTryAgain.setOnClickListener {
            navigateToChooseLevelFragment()
        }
    }

    private fun navigateToChooseLevelFragment() {
        viewModel.navigateToChooseLevelFragment()
    }
}