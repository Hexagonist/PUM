package com.example.lista2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

//import com.example.lista2.databinding.FragmentLoginBinding
import androidx.fragment.app.activityViewModels
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lista2.databinding.E3Binding


class E3 : Fragment() {
    private lateinit var binding: E3Binding

    // Shared ViewModel instance
//    private val usersModel: UsersViewModel by activityViewModels()
    private val list_of_exer_lists: UsersViewModel by activityViewModels()
//    private val args: E3Args by navArgs() // Retrieve the passed ExerciseList

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = E3Binding.inflate(inflater)

//        var index: Int = arguments?.getInt("index")

//        var list = arguments?.getParcelable<ExerciseList>("list")
//        var exerciseList = args.exerciseList

        var exerciseList = arguments?.getParcelable<ExerciseList>("exerciseList")

        binding.recyclerView.apply {
//            adapter = WordListAdapterE3(list_of_exer_lists.getList()[0])
            adapter = exerciseList?.let { WordListAdapterE3(it) }
            layoutManager = LinearLayoutManager(context)
            Toast.makeText(context, "E3", Toast.LENGTH_SHORT).show()


        }


        return binding.root
    }
}