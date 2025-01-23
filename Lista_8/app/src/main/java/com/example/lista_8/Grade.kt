// File: app/src/main/java/com/example/lista_8/Grade.kt
package com.example.lista_8

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grades")
data class Grade(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val subject: String,
    val grade: String
)
