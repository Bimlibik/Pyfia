package io.github.bimlibik.pyfia.ui.topics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import io.github.bimlibik.pyfia.R
import io.github.bimlibik.pyfia.databinding.FragmentTopicsBinding

class TopicsFragment : Fragment() {

    private lateinit var binding: FragmentTopicsBinding

    private val args: TopicsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopicsBinding.inflate(inflater, container, false)
        binding.title = args.title
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbarMenu()
        setupToolbarNavigation()
        setupAdapter()
    }

    private fun setupAdapter() {
        val list: List<String> = resources.getStringArray(R.array.topics).toList()
        binding.topics.adapter = TopicsAdapter()
        (binding.topics.adapter as TopicsAdapter).submitList(list)
    }

    private fun setupToolbarMenu() {
        binding.toolbar.inflateMenu(R.menu.menu_tutorials)
        binding.toolbar.menu.findItem(R.id.item_search).setOnMenuItemClickListener {
            Toast.makeText(requireContext(), "Search", Toast.LENGTH_SHORT).show()
            true
        }
    }

    private fun setupToolbarNavigation() {
        binding.toolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }
    }
}