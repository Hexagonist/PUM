package com.example.lista2
import androidx.lifecycle.ViewModel


class UsersViewModel : ViewModel() {


    var users: MutableList<User> = mutableListOf()

    fun addUser(user: User) {
        users.add(user)
    }

    fun getUsers(): List<User> = users
}