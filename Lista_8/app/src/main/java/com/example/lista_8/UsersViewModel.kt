//package com.example.lista_8
//
//import androidx.lifecycle.ViewModel
//import kotlin.math.round
//import kotlin.random.Random
//
//class UsersViewModel : ViewModel() {
//    lateinit var subjectList: MutableList<Subject>
////    init {
////        // Initialize subjectList with predefined subjects and random grades
////        initializeSubjects()
////    }
//
//    fun initializeSubjects() {
//        val subjectNames = listOf("Matematyka", "PUM", "Fizyka", "PAM")
//        subjectList = subjectNames.map { subjectName ->
//            Subject(
//                name = subjectName,
//                grade = generateRandomGrade()
//            )
//        }.toMutableList()
//    }
//
//    // Generates a random grade between 2.0 and 5.0 with a step of 0.5
//    private fun generateRandomGrade(): Float {
//        val possibleGrades = listOf(2.0f, 2.5f, 3.0f, 3.5f, 4.0f, 4.5f, 5.0f)
//        return possibleGrades.random()
//    }
//
//    // Calculates the mean grade of all subjects
//    fun getSubjectsGradeMean(): Double {
//        if (subjectList.isEmpty()) return 0.0
//        val totalGrades = subjectList.sumOf { it.grade.toDouble() }
//        return totalGrades / subjectList.size
//    }
//
//    fun addSubject(subject: Subject) {
//        subjectList.add(subject)
//    }
//
//    fun updateSubject(oldSubject: Subject, newSubject: Subject) {
//        val index = subjectList.indexOf(oldSubject)
//        if (index != -1) {
//            subjectList[index] = newSubject
//        }
//    }
//
//    fun deleteSubject(subject: Subject) {
//        subjectList.remove(subject)
//    }
//}








//
//package com.example.lista_8
//
//import android.app.Application
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.lista_8.data.SubjectDatabase
//import com.example.lista_8.data.SubjectRepository
//import com.example.lista_8.data.SubjectEntity
//import kotlinx.coroutines.launch
//
//class UsersViewModel(application: Application) : AndroidViewModel(application) {
//
//    private val subjectDao = SubjectDatabase.getDatabase(application).subjectDao()
//    private val repository = SubjectRepository(subjectDao)
//
//    val subjectList = repository.subjectList
//
//    fun initializeSubjects() {
//        viewModelScope.launch {
//            val subjects = repository.getAllSubjects().map { it.toSubject() }
//            subjectList.value = subjects
//        }
//    }
//
//    fun addSubject(subject: Subject) {
//        viewModelScope.launch {
//            repository.addSubject(SubjectEntity(name = subject.name, grade = subject.grade))
//        }
//    }
//
//    fun updateSubject(oldSubject: Subject, updatedSubject: Subject) {
//        viewModelScope.launch {
//            val subjectEntity = SubjectEntity(name = updatedSubject.name, grade = updatedSubject.grade)
//            repository.updateSubject(subjectEntity)
//        }
//    }
//
//    fun deleteSubject(subject: Subject) {
//        viewModelScope.launch {
//            val subjectEntity = SubjectEntity(name = subject.name, grade = subject.grade)
//            repository.deleteSubject(subjectEntity)
//        }
//    }
//
//    fun SubjectEntity.toSubject(): Subject {
//        return Subject(name = this.name, grade = this.grade)
//    }
//}



package com.example.lista_8

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lista_8.data.SubjectDatabase
import com.example.lista_8.data.SubjectRepository
import com.example.lista_8.data.SubjectEntity
import kotlinx.coroutines.launch

class UsersViewModel(application: Application) : AndroidViewModel(application) {

    private val subjectDao = SubjectDatabase.getDatabase(application).subjectDao()
    private val repository = SubjectRepository(subjectDao)

    private val _subjectList = MutableLiveData<List<Subject>>()
    val subjectList: LiveData<List<Subject>> get() = _subjectList

    fun initializeSubjects() {
        viewModelScope.launch {
            val subjects = repository.getAllSubjects().map { it.toSubject() }
            _subjectList.value = subjects // Update the value correctly
        }
    }

    fun addSubject(subject: Subject) {
        viewModelScope.launch {
            repository.addSubject(SubjectEntity(name = subject.name, grade = subject.grade))
            initializeSubjects() // Refresh the list after adding
        }
    }

    fun updateSubject(oldSubject: Subject, updatedSubject: Subject) {
        viewModelScope.launch {
            val subjectEntity = SubjectEntity(name = updatedSubject.name, grade = updatedSubject.grade)
            repository.updateSubject(subjectEntity)
            initializeSubjects() // Refresh the list after updating
        }
    }

    fun deleteSubject(subject: Subject) {
        viewModelScope.launch {
            val subjectEntity = SubjectEntity(name = subject.name, grade = subject.grade)
            repository.deleteSubject(subjectEntity)
            initializeSubjects() // Refresh the list after deleting
        }
    }

    fun SubjectEntity.toSubject(): Subject {
        return Subject(name = this.name, grade = this.grade)
    }
}
