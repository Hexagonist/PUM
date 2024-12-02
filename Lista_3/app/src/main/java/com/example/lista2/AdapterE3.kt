package com.example.lista2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lista2.databinding.ItemE3Binding


class AdapterE3(private val exerciseList: ExerciseList) :
    RecyclerView.Adapter<ViewHolderE3>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderE3 {
        return ViewHolderE3(
            ItemE3Binding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ))
    }

    override fun getItemCount(): Int = exerciseList.exercises.count()

    override fun onBindViewHolder(holder: ViewHolderE3, position: Int) {
        val currentItem = exerciseList.exercises[position]
        holder.bind(currentItem, position)
    }

}