package com.chillandcode.oparator_fragments_kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.chillandcode.oparator_fragments_kotlin.databinding.FragmentGameOverBinding

class GameOverFragment : Fragment() {
    private lateinit var binding: FragmentGameOverBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentGameOverBinding.inflate(layoutInflater)
        binding.retryButton.setOnClickListener{
            findNavController().navigate(R.id.action_gameOverFragment_to_gameFragment)
        }


        return binding.root


    }
}