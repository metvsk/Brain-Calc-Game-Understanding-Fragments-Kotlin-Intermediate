package com.chillandcode.oparator_fragments_kotlin

import android.content.Intent
import android.os.Bundle
import android.view.*
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
        val args=GameWonFragmentArgs.fromBundle(requireArguments())
        val text ="${args.score} / 10"
        binding.score.text = text
        setHasOptionsMenu(true)
        binding.retryButton.setOnClickListener{
            findNavController().navigate(GameOverFragmentDirections.actionGameOverFragmentToGameFragment())
        }
        return binding.root
    }

    private fun getShareIntent(): Intent {
        val args = GameWonFragmentArgs.fromBundle(requireArguments())
        val shareIntent = Intent(Intent.ACTION_SEND)
        val text =
            "${getString(R.string.share_success_text)}${args.score}. Lets see if you can score better... find it at chillandcode.com"
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, text)
        return shareIntent
    }

    private fun shareSuccess() {
        startActivity(getShareIntent())
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.winner_share_menu, menu)
        if (getShareIntent().resolveActivity(requireActivity().packageManager) == null) {
            menu.findItem(R.id.shareSuccess).isVisible = false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.shareSuccess -> shareSuccess()

        }
        return super.onOptionsItemSelected(item)
    }
}