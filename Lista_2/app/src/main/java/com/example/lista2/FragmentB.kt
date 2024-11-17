package com.example.lista2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.lista2.databinding.FragmentABinding
import com.example.lista2.databinding.FragmentBBinding

class FragmentB : Fragment() {

    private lateinit var binding: FragmentBBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBBinding.inflate(inflater)

        binding.textview.text = arguments?.getInt("value").toString() // odebranie danych

        binding.fabB.setOnClickListener {
            val action = FragmentBDirections.actionFragmentBToFragmentA2()
            Navigation.findNavController(requireView()).navigate(action)
        }

        return binding.root
    }
}