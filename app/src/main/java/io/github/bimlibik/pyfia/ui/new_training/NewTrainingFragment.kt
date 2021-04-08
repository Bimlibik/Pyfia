package io.github.bimlibik.pyfia.ui.new_training

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import io.github.bimlibik.pyfia.databinding.FragmentNewTrainingBinding

class NewTrainingFragment : Fragment() {

    private lateinit var binding: FragmentNewTrainingBinding
    private val args: NewTrainingFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewTrainingBinding.inflate(inflater, container, false)
        binding.topic = args.topic
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbarNavigation()
    }

    private fun setupToolbarNavigation() {
        binding.toolbar.setNavigationOnClickListener { view ->
            // show dialog, paused timer
            view.findNavController().navigateUp()
        }
    }
}