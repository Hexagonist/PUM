package com.example.lista2
import androidx.lifecycle.ViewModel
import kotlin.random.Random


class UsersViewModel : ViewModel() {
    var exercise_list: ExerciseList = ExerciseList()

    fun generateExerciseLists(numberOfLists: Int): List<ExerciseList> {
        val subjects = listOf("matematyka", "pum", "fizyka", "elektronika", "algorytmy")

        return List(numberOfLists) {
            val exerciseList = ExerciseList()

            // Assign a random subject from the list
            exerciseList.subject = Subject(name = subjects.random())

            // Assign a random grade between 3.0 and 5.0 with a step of 0.5
            exerciseList.grade = Random.nextInt(6).let { 3.0f + it * 0.5f }

            // Create a random number of exercises (1 to 10)
            val numberOfExercises = Random.nextInt(1, 11) // Upper bound exclusive
            exerciseList.exercises = List(numberOfExercises) {
                Exercise(
                    content = "Exercise Content ${it + 1}", // Example content
                    points = Random.nextInt(0, 11) // Points between 0 and 10
                )
            }.toMutableList()

            exerciseList
        }
    }
}

//    fun addUser(user: User) {
//        users_list.add(user)
//    }

//    fun getUsers(): List<User> = users_list
