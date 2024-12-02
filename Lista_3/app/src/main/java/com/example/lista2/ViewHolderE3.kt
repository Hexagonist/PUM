package com.example.lista2

import androidx.recyclerview.widget.RecyclerView
import com.example.lista2.databinding.ItemE3Binding

class ViewHolderE3(private val binding: ItemE3Binding) :
    RecyclerView.ViewHolder(binding.root){

    fun bind(exercise: Exercise, position: Int){
        binding.taskNumTextView.text = "Zadanie " + (position+1).toString()
        binding.pointsTextView.text = "pkt: " + exercise.points
        binding.exerciseTextView.text = exercise.content
    }
}

