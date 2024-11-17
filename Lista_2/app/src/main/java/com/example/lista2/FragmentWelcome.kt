package com.example.lista2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import com.example.lista2.databinding.FragmentWelcomeBinding

class FragmentWelcome : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWelcomeBinding.inflate(inflater)

//        binding.fabA.setOnClickListener {
//            val action = FragmentMMenuDirections.actionFragmentMMenuToFragmentB(5)
//            Navigation.findNavController(requireView()).navigate(action)
//        }

        binding.buttonLogout.setOnClickListener{
            val action = FragmentWelcomeDirections.actionFragmentWelcomeToFragmentMMenu()
            Navigation.findNavController(requireView()).navigate(action)
        }


        return binding.root
    }
}