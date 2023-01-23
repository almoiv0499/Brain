package ru.game.brain.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.game.brain.data.repository.GameRepositoryImpl
import ru.game.brain.databinding.FragmentGameBinding
import ru.game.brain.domain.model.GameResult
import ru.game.brain.domain.usecase.GenerateQuestionUseCase
import ru.game.brain.domain.usecase.GetGameSettingsUseCase
import ru.game.brain.presentation.viewmodel.GameViewModel
import ru.game.brain.presentation.viewmodelfactory.GameViewModelFactory

class GameFragment : Fragment() {

    private val args by navArgs<GameFragmentArgs>()

    private val repository by lazy(LazyThreadSafetyMode.NONE) {
        GameRepositoryImpl
    }

    private val getGameSettingsUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetGameSettingsUseCase(repository)
    }

    private val generateQuestionUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GenerateQuestionUseCase(repository)
    }

    private val viewModelFactory by lazy(LazyThreadSafetyMode.NONE) {
        GameViewModelFactory(
            generateQuestionUseCase = generateQuestionUseCase,
            getGameSettingsUseCase = getGameSettingsUseCase,
            application = requireActivity().application,
            level = args.level
        )
    }

    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(this, viewModelFactory)[GameViewModel::class.java]
    }

    private lateinit var binding: FragmentGameBinding

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
            toResultScreen(it)
        }
    }

    private fun toResultScreen(gameResults: GameResult) {
        findNavController().navigate(
            GameFragmentDirections.actionGameFragment2ToGameFinishedFragment(gameResults)
        )
    }
}