package com.example.lista2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lista2.databinding.E2Binding
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager

class E2 : Fragment() {

    private lateinit var binding: E2Binding
    private val list_of_exer_lists: UsersViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = E2Binding.inflate(inflater)

        binding.recyclerView.apply {
            adapter = AdapterE2(list_of_exer_lists.getSubjectsSummaryList())
            layoutManager = LinearLayoutManager(context)
        }

        return binding.root
    }
}