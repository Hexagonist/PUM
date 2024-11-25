package com.example.lista2

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.lista2.databinding.WordListItemBinding

class WordListViewHolder(private val binding: WordListItemBinding) :
    RecyclerView.ViewHolder(binding.root){

    fun bind(list_of_exercises: ExerciseList){
        binding.singleWord.text = list_of_exercises.subject.name
        binding.singleWord2.text = list_of_exercises.subject.name
//        binding.singleWord2.text = item
//        binding.singleWord2.text = item
    }
}

