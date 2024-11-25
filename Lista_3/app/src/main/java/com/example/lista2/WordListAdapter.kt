package com.example.lista2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lista2.databinding.WordListItemBinding


class WordListAdapter(private val exerciseListList: List<ExerciseList>) :
    RecyclerView.Adapter<WordListViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordListViewHolder {
        return WordListViewHolder(
            WordListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ))
    }

    override fun getItemCount(): Int = exerciseListList.size

    override fun onBindViewHolder(holder: WordListViewHolder, position: Int) {
        val currentItem = exerciseListList[position]
        holder.bind(currentItem, position)
    }

}