package com.example.lista2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.lista2.databinding.FragmentRegisterBinding
import androidx.fragment.app.activityViewModels

class FragmentRegister : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    // Shared ViewModel instance
    private val usersModel: UsersViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater)

//        binding.textview.text = arguments?.getInt("value").toString() // odebranie danych

//        binding.fabB.setOnClickListener {
//            val action = FragmentRegisterBinding.actionFragmentBToFragmentA2()
//            Navigation.findNavController(requireView()).navigate(action)
//        }

        binding.buttonRegister.setOnClickListener{
            var input_login = binding.textInputLogin.editText?.text.toString()
            var input_password = binding.textInputHaslo.editText?.text.toString()
            var input_repeatedPassword = binding.textInputPowtorzHaslo.editText?.text.toString()
            var all_users = usersModel.getUsers()

            // User already exists
            if( all_users.any{it.login == input_login} )
            {
                Toast.makeText(requireContext(), "User $input_login already exists!", Toast.LENGTH_SHORT).show()
            }
            // wrong repeated password
            else if(input_password != input_repeatedPassword)
            {
                Toast.makeText(requireContext(), "Passwords must be the same!", Toast.LENGTH_SHORT).show()
            }
            // success
            else
            {
                usersModel.addUser(User(input_login, input_password))
                val action = FragmentRegisterDirections.actionFragmentRegisterToFragmentLogin()
                Navigation.findNavController(requireView()).navigate(action)
            }
        }

        return binding.root
    }
}