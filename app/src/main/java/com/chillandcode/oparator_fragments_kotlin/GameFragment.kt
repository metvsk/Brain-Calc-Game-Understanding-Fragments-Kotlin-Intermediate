package com.chillandcode.oparator_fragments_kotlin

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView.OnEditorActionListener
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.chillandcode.oparator_fragments_kotlin.databinding.FragmentGameBinding
import com.google.android.material.snackbar.Snackbar
import java.math.RoundingMode
import java.text.DecimalFormat


class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        updateQuestion()

        binding.skipButton.setOnClickListener {
            viewModel.generateQuestion()
            viewModel.reduceCounter()
            binding.answer.text.clear()
            reloadRemainingCounter()
            updateQuestion()
        }
        reloadRemainingCounter()
        reloadScore()
        binding.answer.setOnEditorActionListener(OnEditorActionListener { _, actionId, event ->
            if (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_DONE) {
                binding.submitButton.requestFocus()
                binding.submitButton.callOnClick()
            }
            false
        })
        binding.submitButton.setOnClickListener { view: View ->
            if (binding.answer.text.isNullOrBlank()) {

                snackIt(view, "Please enter answer or skip this question !", true)
            } else {

                try {

                    val input: Float = binding.answer.text.toString().toFloat()
                    if (viewModel.getResult()== input) {
                        snackIt(view, "Correct Answer", false)
                        viewModel.addPoint()
                        viewModel.reduceCounter()
                        reloadScore()
                        reloadRemainingCounter()
                        binding.answer.text.clear()
                        viewModel.generateQuestion()
                        updateQuestion()
                    } else {
                        snackIt(view, "Wrong answer 1 point reduced", true)
                        viewModel.reducePoint()
                        viewModel.reduceCounter()
                        reloadRemainingCounter()
                        reloadScore()
                    }
                } catch (e: Exception) {
                    snackIt(view, "Incorrect value", true)
                    binding.answer.text.clear()
                }

            }
        }

        return binding.root
    }

    private fun updateQuestion() {
        binding.questionTextView.text = viewModel.getQuestion()

    }

    private fun reloadRemainingCounter() {
        when {
            viewModel.getCounter() > 0 -> binding.remainingTV.text = viewModel.getCounter().toString()
            viewModel.getScore() == 10 ->{ findNavController().navigate(R.id.action_gameFragment_to_gameWonFragment)
                view?.hideKeyboard()
            }
            else -> {findNavController().navigate(R.id.action_gameFragment_to_gameOverFragment)
                view?.hideKeyboard()
            }
        }
    }

    private fun reloadScore() {
        val text = "Score : ${viewModel.getScore()} / 10"
        binding.scoreTV.text = text
    }

    private fun snackIt(view: View, message: String, warn: Boolean) {
        val drawableBackGround: Int = if (warn)
            R.drawable.snack_warn_corner
        else
            R.drawable.snack_success_corner

        Utilities.snackIt(
            requireContext(),
            view,
            message,
            Snackbar.LENGTH_SHORT,
            drawableBackGround,
            Gravity.CENTER,
            Color.WHITE,
            null
        )
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

}