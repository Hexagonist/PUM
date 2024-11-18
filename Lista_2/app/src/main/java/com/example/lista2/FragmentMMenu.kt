package com.example.lista2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import com.example.lista2.databinding.FragmentMmenuBinding

class FragmentMMenu : Fragment() {
    private lateinit var binding: FragmentMmenuBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMmenuBinding.inflate(inflater)

        binding.buttonLogin.setOnClickListener{
            val action = FragmentMMenuDirections.actionFragmentMMenuToFragmentLogin()
            Navigation.findNavController(requireView()).navigate(action)
        }

        binding.buttonRegister.setOnClickListener{
            val action = FragmentMMenuDirections.actionFragmentMMenuToFragmentRegister()
            Navigation.findNavController(requireView()).navigate(action)
        }


        return binding.root
    }
}