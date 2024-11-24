package com.example.lista2

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.lista2.databinding.WordListItemBinding

class WordListViewHolder(private val binding: WordListItemBinding) :
    RecyclerView.ViewHolder(binding.root){

    fun bind(item: String){
        binding.singleWord.text = item
        binding.singleWord2.text = item
    }
}

