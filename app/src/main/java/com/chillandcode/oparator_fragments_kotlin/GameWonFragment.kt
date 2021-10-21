package com.chillandcode.oparator_fragments_kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.chillandcode.oparator_fragments_kotlin.databinding.FragmentGameWonBinding

class GameWonFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentGameWonBinding.inflate(layoutInflater)


        binding.button.setOnClickListener {
            findNavController().navigate(GameWonFragmentDirections.actionGameWonFragmentToGameFragment())

        }
        return binding.root
    }

}