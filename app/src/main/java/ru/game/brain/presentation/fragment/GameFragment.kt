package ru.game.brain.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.game.app.App
import ru.game.brain.databinding.FragmentGameBinding
import ru.game.brain.domain.model.GameResult
import ru.game.brain.presentation.fragment.base.BaseFragment
import ru.game.brain.presentation.viewmodel.GameViewModel
import ru.game.brain.presentation.viewmodelfactory.GameViewModelFactory
import ru.game.di.component.DaggerAppComponent
import javax.inject.Inject

class GameFragment : BaseFragment<GameViewModel>() {

    private val args by navArgs<GameFragmentArgs>()

    @Inject
    lateinit var viewModelFactory: GameViewModelFactory

    override val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(this, viewModelFactory)[GameViewModel::class.java]
    }

    private val component by lazy(LazyThreadSafetyMode.NONE) {
        (requireActivity().applicationContext as App)
            .component
            .appSubcomponentFactory()
            .create(args.level)
    }

    private lateinit var binding: FragmentGameBinding

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModel()
        observeViewModel()
    }

    private fun bindViewModel() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun observeViewModel() {
        viewModel.liveDataGameResult.observe(viewLifecycleOwner) {
            navigateToGameFinishedFragment(it)
        }
    }

    private fun navigateToGameFinishedFragment(gameResults: GameResult) {
        viewModel.navigateToGameFinishedFragment(gameResults)
    }
}