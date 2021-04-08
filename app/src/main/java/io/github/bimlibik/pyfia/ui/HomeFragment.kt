package io.github.bimlibik.pyfia.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import io.github.bimlibik.pyfia.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigate()
    }

    private fun navigate() {
        binding.btnTutorial.btn.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeToTutorials()
            findNavController().navigate(action)
        }

        binding.btnTraining.btn.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeToTraining()
            findNavController().navigate(action)
        }
    }
}