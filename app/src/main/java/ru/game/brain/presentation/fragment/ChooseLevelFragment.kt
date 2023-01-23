package ru.game.brain.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.game.brain.databinding.FragmentChooseLevelBinding
import ru.game.brain.domain.model.Level

class ChooseLevelFragment : Fragment() {

    private lateinit var binding: FragmentChooseLevelBinding

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
                toNextScreen(Level.TEST)
            }
            buttonLevelEasy.setOnClickListener {
                toNextScreen(Level.LOW)
            }
            buttonLevelMiddle.setOnClickListener {
                  toNextScreen(Level.MIDDLE)
            }
            buttonLevelHard.setOnClickListener {
                toNextScreen(Level.HIGH)
            }
        }
    }

    private fun toNextScreen(level: Level) {
        findNavController().navigate(
            ChooseLevelFragmentDirections.actionChooseLevelFragmentToGameFragment2(level)
        )
    }
}