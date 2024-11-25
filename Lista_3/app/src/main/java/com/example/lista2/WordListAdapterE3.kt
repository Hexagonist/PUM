//package com.example.lista2
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.lista2.databinding.WordListItemE3Binding
//
//
//class WordListAdapterE3(private val exerciseList: ExerciseList) :
//    RecyclerView.Adapter<WordListViewHolderE3>(){
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordListViewHolderE3 {
//        return WordListViewHolderE3(
//            WordListItemE3Binding.inflate(
//                LayoutInflater.from(parent.context), parent, false
//            ))
//    }
//
//    override fun getItemCount(): Int = exerciseList.
//
//    override fun onBindViewHolder(holder: WordListViewHolderE3, position: Int) {
//        val currentItem = exerciseList[position]
//        holder.bind(currentItem, position)
//    }
//
//}