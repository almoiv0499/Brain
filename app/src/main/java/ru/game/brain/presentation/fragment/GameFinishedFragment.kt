package ru.game.brain.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.game.brain.databinding.FragmentGameFinishedBinding

class GameFinishedFragment : Fragment() {

    private lateinit var binding: FragmentGameFinishedBinding

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
            retryGame()
        }
    }
    private fun retryGame() {
        findNavController().popBackStack()
    }
}