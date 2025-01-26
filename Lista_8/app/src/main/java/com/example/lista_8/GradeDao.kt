package com.example.lista_8

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GradeDao {
    @Query("SELECT * FROM grades")
    fun getAllGrades(): LiveData<List<Grade>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGrade(grade: Grade)

    @Update
    suspend fun updateGrade(grade: Grade)

    @Delete
    suspend fun deleteGrade(grade: Grade)
}
