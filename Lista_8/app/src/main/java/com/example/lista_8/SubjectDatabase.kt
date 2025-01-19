package com.example.lista_8.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SubjectEntity::class], version = 1)
abstract class SubjectDatabase : RoomDatabase() {

    abstract fun subjectDao(): SubjectDao

    companion object {
        @Volatile
        private var INSTANCE: SubjectDatabase? = null

        fun getDatabase(context: Context): SubjectDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SubjectDatabase::class.java,
                    "subject_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
