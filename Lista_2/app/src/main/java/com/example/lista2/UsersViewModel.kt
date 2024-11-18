package com.example.lista2
import androidx.lifecycle.ViewModel


class UsersViewModel : ViewModel() {


    var users_list: MutableList<User> = mutableListOf()

    fun addUser(user: User) {
        users_list.add(user)
    }

    fun getUsers(): List<User> = users_list
}