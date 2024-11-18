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
import com.example.lista2.databinding.E3Binding


class E3 : Fragment() {
    private lateinit var binding: E3Binding

    // Shared ViewModel instance
    private val usersModel: UsersViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        binding = FragmentLoginBinding.inflate(inflater)
//
//
//        binding.buttonLogin.setOnClickListener{
//            var input_login = binding.textInputLogin.editText?.text.toString()
//            var input_password = binding.textInputHaslo.editText?.text.toString()
//            var all_users = usersModel.getUsers()
//
//            // login doesn't exist
//            if( !all_users.any{it.login == input_login} )
//            {
//                Toast.makeText(requireContext(), "User $input_login not found!", Toast.LENGTH_SHORT).show()
//            }
//            // wrong password
//            else if(all_users.find {it.login == input_login}?.password != input_password)
//            {
//                Toast.makeText(requireContext(), "Incorrect password!", Toast.LENGTH_SHORT).show()
//            }
//            // success
//            else
//            {
//                var user_login = all_users.find {it.login == input_login}?.login
//                val action = FragmentLoginDirections.actionFragmentLoginToFragmentWelcome(user_login.toString())
//                Navigation.findNavController(requireView()).navigate(action)
//            }
//        }
//
//        binding.buttonRegister.setOnClickListener{
//            val action = FragmentLoginDirections.actionFragmentLoginToFragmentRegister2()
//            Navigation.findNavController(requireView()).navigate(action)
//        }


        return binding.root
    }
}