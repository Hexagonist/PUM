// File: app/src/main/java/com/example/lista_8/GradeRepository.kt
package com.example.lista_8

import androidx.lifecycle.LiveData

class GradeRepository(private val gradeDao: GradeDao) {
    val allGrades: LiveData<List<Grade>> = gradeDao.getAllGrades()

    suspend fun insert(grade: Grade) {
        gradeDao.insertGrade(grade)
    }

    suspend fun update(grade: Grade) {
        gradeDao.updateGrade(grade)
    }

    suspend fun delete(grade: Grade) {
        gradeDao.deleteGrade(grade)
    }
}
