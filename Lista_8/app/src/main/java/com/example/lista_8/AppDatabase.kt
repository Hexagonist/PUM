package com.example.lista_8
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lista_8.Grade
import com.example.lista_8.GradeDao

//
//import androidx.room.Database
//
//@Database(entities = [User::class], version = 1, exportSchema = false)
//abstract class AppDatabase : RoomDatabase() {
//    abstract fun userDao(): UserDao
//
//    companion object {
//        @Volatile
//        private var Instance: UserDatabase? = null
//
//        fun getDatabase(context: Context): UserDatabase {
//            return Instance ?: synchronized(this) {
//                Room.databaseBuilder(context, UserDatabase::class.java, "user_database")
//                    .build()
//                    .also { Instance = it }
//            }
//        }
//    }
//}

// 3. Baza danych
@Database(entities = [Grade::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gradeDao(): GradeDao
}