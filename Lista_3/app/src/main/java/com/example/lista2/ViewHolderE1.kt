package com.example.lista2

import androidx.recyclerview.widget.RecyclerView
import com.example.lista2.databinding.WordListItemBinding

class ViewHolderE1(
    private val binding: WordListItemBinding,
    onItemClick: (Int) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {
        init {
        itemView.setOnClickListener {
            onItemClick(adapterPosition)
        }
    }
    fun bind(list_of_exercises: ExerciseList, position: Int){
    binding.subjectTextView.text = list_of_exercises.subject.name
    binding.listNumTextView.text = "Lista: " + position.toString()
    binding.exerNumTextView.text = "Liczba zadań: " + list_of_exercises.exercises.count().toString()
    binding.gradeTextView.text = "Ocena: " + list_of_exercises.grade.toString()
    }
}
