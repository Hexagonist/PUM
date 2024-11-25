package com.example.lista2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

//import com.example.lista2.databinding.FragmentLoginBinding
import androidx.fragment.app.activityViewModels
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lista2.databinding.E3Binding


class E3 : Fragment() {
    private lateinit var binding: E3Binding

    // Shared ViewModel instance
    private val usersModel: UsersViewModel by activityViewModels()
    private val list_of_exer_lists: UsersViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        binding = FragmentLoginBinding.inflate(inflater)
//
//

        binding.recyclerView.apply {
            adapter = WordListAdapterE3(list_of_exer_lists.getList()[0])
            layoutManager = LinearLayoutManager(context)
        }


        return binding.root
    }
}