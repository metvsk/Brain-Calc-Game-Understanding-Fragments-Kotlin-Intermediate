package com.chillandcode.oparator_fragments_kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chillandcode.oparator_fragments_kotlin.databinding.FragmentRulesBinding

class RulesFragment : Fragment() {
    private lateinit var binding: FragmentRulesBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRulesBinding.inflate(inflater)
        return binding.root
    }

}