package com.example.lista_8

import androidx.lifecycle.ViewModel
import kotlin.math.round
import kotlin.random.Random

class UsersViewModel : ViewModel() {
    lateinit var subjectList: MutableList<Subject>
//    init {
//        // Initialize subjectList with predefined subjects and random grades
//        initializeSubjects()
//    }

    fun initializeSubjects() {
        val subjectNames = listOf("Matematyka", "PUM", "Fizyka", "PAM")
        subjectList = subjectNames.map { subjectName ->
            Subject(
                name = subjectName,
                grade = generateRandomGrade()
            )
        }.toMutableList()
    }

    // Generates a random grade between 2.0 and 5.0 with a step of 0.5
    private fun generateRandomGrade(): Float {
        val possibleGrades = listOf(2.0f, 2.5f, 3.0f, 3.5f, 4.0f, 4.5f, 5.0f)
        return possibleGrades.random()
    }

    // Calculates the mean grade of all subjects
    fun getSubjectsGradeMean(): Double {
        if (subjectList.isEmpty()) return 0.0
        val totalGrades = subjectList.sumOf { it.grade.toDouble() }
        return totalGrades / subjectList.size
    }

    fun addSubject(subject: Subject) {
        subjectList.add(subject)
    }

    fun updateSubject(oldSubject: Subject, newSubject: Subject) {
        val index = subjectList.indexOf(oldSubject)
        if (index != -1) {
            subjectList[index] = newSubject
        }
    }

    fun deleteSubject(subject: Subject) {
        subjectList.remove(subject)
    }
}