package com.example.lista2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import com.example.lista2.databinding.FragmentLoginBinding

class FragmentLogin : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater)

//        binding.fabA.setOnClickListener {
//            val action = FragmentMMenuDirections.actionFragmentMMenuToFragmentB(5)
//            Navigation.findNavController(requireView()).navigate(action)
//        }

        binding.buttonLogin.setOnClickListener{
            val action = FragmentLoginDirections.actionFragmentLoginToFragmentWelcome()
            Navigation.findNavController(requireView()).navigate(action)
        }

        binding.buttonRegister.setOnClickListener{
            val action = FragmentLoginDirections.actionFragmentLoginToFragmentRegister2()
            Navigation.findNavController(requireView()).navigate(action)
        }


        return binding.root
    }
}