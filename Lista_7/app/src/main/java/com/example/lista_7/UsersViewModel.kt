package com.example.lista_7

import androidx.lifecycle.ViewModel
import kotlin.math.round
import kotlin.random.Random

class UsersViewModel : ViewModel() {
    lateinit var listOfStudents: List<Student>

    fun generateDummyStudents(num : Int) {
        // Predefined lists of random names and surnames
        val names = listOf("John", "Alice", "Mark", "Sophia", "David", "Emma", "Michael", "Olivia", "William", "Ava")
        val surnames = listOf("Smith", "Johnson", "Brown", "Williams", "Jones", "Garcia", "Miller", "Davis", "Martinez", "Hernandez")

        // Function to generate a random mean value in the range [2.0, 5.0] with a step of 0.5
        fun generateRandomMean(): Float {
            val possibleMeans = listOf(2.0f, 2.5f, 3.0f, 3.5f, 4.0f, 4.5f, 5.0f)
            return possibleMeans.random()
        }

        // Generate 10 dummy students
        listOfStudents = List(num) {
            Student(
                indexNum = Random.nextInt(100000, 999999).toString(), // Generate 6-digit random index
                name = names.random(),
                surname = surnames.random(),
                mean = generateRandomMean().toString(),
                yearOfStudy = Random.nextInt(1, 5).toString() // Generate random year from 1-4
            )
        }
    }

//    // Example usage
//    fun main() {
//        val dummyStudents = generateDummyStudents()
//        dummyStudents.forEach { println(it) }
//    }

}