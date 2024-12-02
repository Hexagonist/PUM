package com.example.lista2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lista2.databinding.WordListItemBinding


class AdapterE2(private val exerciseListList: List<ExerciseList>) :
    RecyclerView.Adapter<ViewHolderE2>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderE2 {
        return ViewHolderE2(
            WordListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ))
    }

    override fun getItemCount(): Int = exerciseListList.size

    override fun onBindViewHolder(holder: ViewHolderE2, position: Int) {
        val currentItem = exerciseListList[position]
        holder.bind(currentItem, position)
    }

}