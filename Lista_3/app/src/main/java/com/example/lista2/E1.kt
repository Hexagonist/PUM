package com.example.lista2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.lista2.databinding.E1Binding

class E1 : Fragment() {
    private lateinit var binding: E1Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = E1Binding.inflate(inflater)

//        binding.buttonLogin.setOnClickListener{
//            val action = FragmentMMenuDirections.actionFragmentMMenuToFragmentLogin()
//            Navigation.findNavController(requireView()).navigate(action)
//        }
//
//        binding.buttonRegister.setOnClickListener{
//            val action = FragmentMMenuDirections.actionFragmentMMenuToFragmentRegister()
//            Navigation.findNavController(requireView()).navigate(action)
//        }

        val wordList by lazy { MutableList(6) { "word $it" } }
        binding.recyclerView.apply {
            adapter = WordListAdapter(wordList)
            layoutManager = LinearLayoutManager(context)
        }



        return binding.root
    }
}