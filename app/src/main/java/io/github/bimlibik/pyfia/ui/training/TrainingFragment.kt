package io.github.bimlibik.pyfia.ui.training

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import io.github.bimlibik.pyfia.R
import io.github.bimlibik.pyfia.databinding.FragmentTrainingBinding

class TrainingFragment : Fragment() {

    private lateinit var binding: FragmentTrainingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrainingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbarMenu()
        setupToolbarNavigation()
        setupAdapter()
    }

    private fun setupAdapter() {
        val list: List<String> = resources.getStringArray(R.array.tutorials).toList()
        binding.tutorials.adapter = TrainingAdapter()
        (binding.tutorials.adapter as TrainingAdapter).submitList(list)
    }

    private fun setupToolbarMenu() {
        binding.toolbar.inflateMenu(R.menu.menu_training)
        binding.toolbar.menu.findItem(R.id.item_help).setOnMenuItemClickListener {
            Toast.makeText(requireContext(), "Tooltip", Toast.LENGTH_SHORT).show()
            true
        }
    }

    private fun setupToolbarNavigation() {
        binding.toolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }
    }
}