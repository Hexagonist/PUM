package com.example.lista2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel


//class UsersViewModel : ViewModel() {
//    class User (var login : String = "",
//            var password : String = "")
//
//    var users: MutableList<User> = mutableListOf()
//
//    fun addUser(user: User) {
//        users.add(user)
//    }
//
//    fun getUsers(): List<User> = users
//}


class MainActivity : AppCompatActivity() {
    private val userViewModel: UsersViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        // Create basic list of 5 users
        for (nums in 1..5){
            userViewModel.addUser(User("user_$nums", nums.toString()))
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
}