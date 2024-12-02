package com.example.lista2

import androidx.recyclerview.widget.RecyclerView
import com.example.lista2.databinding.WordListItemBinding

class ViewHolderE2(private val binding: WordListItemBinding) :
    RecyclerView.ViewHolder(binding.root){

    fun bind(list_of_exercises: ExerciseList, position: Int){
        binding.subjectTextView.text = list_of_exercises.subject.name
        binding.listNumTextView.text = "Średnia: " + list_of_exercises.grade.toString()
        binding.exerNumTextView.text = "Liczba list: " + list_of_exercises.listNum.toString()
        binding.gradeTextView.text = ""
    }
}

