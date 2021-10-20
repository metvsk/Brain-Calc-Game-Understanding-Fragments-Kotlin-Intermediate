package com.chillandcode.oparator_fragments_kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.chillandcode.oparator_fragments_kotlin.databinding.FragmentTitleScreenBinding

class TitleFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding=FragmentTitleScreenBinding.inflate(layoutInflater)
        binding.playButton.setOnClickListener{
            findNavController().navigate(R.id.action_titleFragment_to_gameFragment)
        }
        return binding.root
    }
}