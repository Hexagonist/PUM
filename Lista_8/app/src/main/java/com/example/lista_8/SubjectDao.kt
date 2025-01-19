package com.example.lista_8.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SubjectDao {

    @Insert
    suspend fun insert(subject: SubjectEntity)

    @Update
    suspend fun update(subject: SubjectEntity)

    @Delete
    suspend fun delete(subject: SubjectEntity)

    @Query("SELECT * FROM subjects")
    suspend fun getAllSubjects(): List<SubjectEntity>

    @Query("SELECT * FROM subjects WHERE name = :name LIMIT 1")
    suspend fun getSubjectByName(name: String): SubjectEntity?
}
