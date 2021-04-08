package io.github.bimlibik.pyfia.ui.topic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import io.github.bimlibik.pyfia.databinding.FragmentTopicBinding

class TopicFragment : Fragment() {

    private lateinit var binding: FragmentTopicBinding

    private val args: TopicFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopicBinding.inflate(inflater, container, false)
        binding.tutorialTitle = args.tutorialTitle
        binding.topicTitle = args.topicTitle
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbarNavigation()
    }

    private fun setupToolbarNavigation() {
        binding.toolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }
    }
}