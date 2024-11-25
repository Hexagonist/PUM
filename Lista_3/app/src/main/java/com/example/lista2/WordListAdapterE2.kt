package com.example.lista2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lista2.databinding.WordListItemBinding


class WordListAdapterE2(private val exerciseListList: List<ExerciseList>) :
    RecyclerView.Adapter<WordListViewHolderE2>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordListViewHolderE2 {
        return WordListViewHolderE2(
            WordListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ))
    }

    override fun getItemCount(): Int = exerciseListList.size

    override fun onBindViewHolder(holder: WordListViewHolderE2, position: Int) {
        val currentItem = exerciseListList[position]
        holder.bind(currentItem, position)
    }

}