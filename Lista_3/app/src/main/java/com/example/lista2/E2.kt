package com.example.lista2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.lista2.databinding.E2Binding
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager

class E2 : Fragment() {

    private lateinit var binding: E2Binding
    private val list_of_exer_lists: UsersViewModel by activityViewModels()


    // Shared ViewModel instance
    private val usersModel: UsersViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = E2Binding.inflate(inflater)

//        binding.buttonRegister.setOnClickListener{
//            var input_login = binding.textInputLogin.editText?.text.toString()
//            var input_password = binding.textInputHaslo.editText?.text.toString()
//            var input_repeatedPassword = binding.textInputPowtorzHaslo.editText?.text.toString()
//            var all_users = usersModel.getUsers()
//
//            // User already exists
//            if( all_users.any{it.login == input_login} )
//            {
//                Toast.makeText(requireContext(), "User $input_login already exists!", Toast.LENGTH_SHORT).show()
//            }
//            // wrong repeated password
//            else if(input_password != input_repeatedPassword)
//            {
//                Toast.makeText(requireContext(), "Passwords must be the same!", Toast.LENGTH_SHORT).show()
//            }
//            else if(input_login == "")
//            {
//                Toast.makeText(requireContext(), "Login cannot be empty!", Toast.LENGTH_SHORT).show()
//            }
//            else if(input_password == "")
//            {
//                Toast.makeText(requireContext(), "Password cannot be empty!", Toast.LENGTH_SHORT).show()
//            }
//            // success
//            else
//            {
//                usersModel.addUser(User(input_login, input_password))
//                val action = FragmentRegisterDirections.actionFragmentRegisterToFragmentLogin()
//                Navigation.findNavController(requireView()).navigate(action)
//            }
//        }
        binding.recyclerView.apply {
            adapter = WordListAdapterE2(list_of_exer_lists.getSubjectsSummaryList())
            layoutManager = LinearLayoutManager(context)
        }

        return binding.root
    }
}