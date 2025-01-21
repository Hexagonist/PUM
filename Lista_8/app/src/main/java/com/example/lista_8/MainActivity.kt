package com.example.lista_8

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lista_8.ui.theme.Lista_8Theme

// app/src/main/java/com/example/gradesapp


import android.app.Application
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.dp
import androidx.lifecycle.*
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.*
import androidx.room.Entity
import kotlinx.coroutines.launch




// 1. Model danych
@Entity(tableName = "grades")
data class Grade(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val score: Float
)

// 2. DAO
@Dao
interface GradeDao {
    @Query("SELECT * FROM grades")
    fun getAll(): LiveData<List<Grade>>

    @Insert
    suspend fun insert(grade: Grade)

    @Update
    suspend fun update(grade: Grade)

    @Delete
    suspend fun delete(grade: Grade)
}

//// 3. Baza danych
//@Database(entities = [Grade::class], version = 1)
//abstract class AppDatabase : RoomDatabase() {
//    abstract fun gradeDao(): GradeDao
//}

class GradeViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GradeViewModel::class.java)) {
            return GradeViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

// 4. ViewModel
class GradeViewModel(application: Application) : AndroidViewModel(application) {
    private val db = Room.databaseBuilder(
        application, AppDatabase::class.java, "grades.db"
    ).build()

    val grades = db.gradeDao().getAll()
    private val dao = db.gradeDao()

    fun addGrade(grade: Grade) = viewModelScope.launch { dao.insert(grade) }
    fun updateGrade(grade: Grade) = viewModelScope.launch { dao.update(grade) }
    fun deleteGrade(grade: Grade) = viewModelScope.launch { dao.delete(grade) }
}

// 5. Jetpack Compose UI
@Composable
fun GradeApp(viewModel: GradeViewModel) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "list") {
        composable("list") { GradeListScreen(viewModel, navController) }
        composable("edit/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toInt() ?: 0
            GradeEditScreen(viewModel, navController, id)
        }
        composable("add") { GradeAddScreen(viewModel, navController) }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GradeListScreen(viewModel: GradeViewModel, navController: NavController) {
    val grades by viewModel.grades.observeAsState(emptyList())

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("add") }) {
                Text("Nowy")
            }
        }
    ) {
//        LazyColumn(modifier = Modifier.fillMaxSize()) {
//            items(grades) { grade ->
//                ListItem(
//                    headlineText = { Text(grade.name) },
//                    supportingText = { Text("Ocena: ${grade.score}") },
//                    modifier = Modifier.clickable {
//                        navController.navigate("edit/${grade.id}")
//                    }
//                )
//            }
//        }
    }
}



@Composable
fun GradeEditScreen(viewModel: GradeViewModel, navController: NavController, id: Int) {
    val grades by viewModel.grades.observeAsState(emptyList())
    val grade = grades.find { it.id == id }

    if (grade != null) {
        var name by remember { mutableStateOf(grade.name) }
        var score by remember { mutableStateOf(grade.score.toString()) }

        Column(Modifier.padding(16.dp)) {
            Text("Edytuj wpis")
            Spacer(Modifier.height(8.dp))

            BasicTextField(
                value = name,
                onValueChange = { name = it },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))

            BasicTextField(
                value = score,
                onValueChange = { score = it },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))

            Button(onClick = {
                viewModel.updateGrade(Grade(grade.id, name, score.toFloat()))
                navController.popBackStack()
            }) {
                Text("Zapisz")
            }

            Spacer(Modifier.height(8.dp))

            Button(onClick = {
                viewModel.deleteGrade(grade)
                navController.popBackStack()
            }) {
                Text("Usuń")
            }
        }
    }
}

@Composable
fun GradeAddScreen(viewModel: GradeViewModel, navController: NavController) {
    var name by remember { mutableStateOf("") }
    var score by remember { mutableStateOf("") }

    Column(Modifier.padding(16.dp)) {
        Text("Dodaj nowy wpis")
        Spacer(Modifier.height(8.dp))

        BasicTextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        BasicTextField(
            value = score,
            onValueChange = { score = it },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        Button(onClick = {
            viewModel.addGrade(Grade(name = name, score = score.toFloat()))
            navController.popBackStack()
        }) {
            Text("Dodaj")
        }
    }
}

class MainActivity : ComponentActivity() {
    // Exercise lists
//    private val listOfGrades: GradeViewModel by viewModels()
    private val listOfGrades: GradeViewModel by viewModels{
        GradeViewModelFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Generate data
//        listOfGrades.generateDummyStudents(20F)

        setContent {
            Lista_8Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GradeApp(listOfGrades)
                }
            }
        }
    }
}