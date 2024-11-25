package com.example.lista2

import androidx.recyclerview.widget.RecyclerView
import com.example.lista2.databinding.WordListItemBinding
import com.example.lista2.databinding.WordListItemE3Binding

class WordListViewHolderE3(private val binding: WordListItemE3Binding) :
    RecyclerView.ViewHolder(binding.root){

    fun bind(exercise: Exercise, position: Int){
        binding.taskNumTextView.text = "taskNum"
        binding.pointsTextView.text = "pkt: "
        binding.exerciseTextView.text = "Treść zadania "
    }
}

