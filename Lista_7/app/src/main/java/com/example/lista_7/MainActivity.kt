package com.example.lista_7

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
//import com.example.lista_6_2.ExerciseList
import com.example.lista_7.ui.theme.Lista_7Theme

class MainActivity : ComponentActivity() {
    // Exercise lists
    private val listOfStudents: UsersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Generate data
        listOfStudents.generateDummyStudents(20)

        setContent {
            Lista_7Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation(listOfStudents)
                }
            }
        }
    }
}

sealed class Screens(val route: String) {
    data object MasterScreen : Screens("master_screen")
    data object DetailScreen : Screens("detail_screen")
}

@Composable
fun MasterScreen(
    onDetailScreen: (Any?) -> Unit,
    studentList: List<Student>
) {
    LazyColumn{
        itemsIndexed(studentList) { index, student ->
            StudentListCard(
                student = student,
                onClick = { onDetailScreen(index) }
            )
        }
    }
}

@Composable
fun StudentListCard(student: Student, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Imię: ${student.name}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Indeks: ${student.indexNum}", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Nazwisko: ${student.surname}")
            }
        }
    }
}

@Composable
fun DetailScreen(arg: String?, studentList: List<Student>) {
    LazyColumn {
        if (arg != null) {
            item {
                ExerciseCard(studentList[arg.toInt()])
            }
        }
    }
}

@Composable
fun ExerciseCard(student: Student) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "Imię i Nazwisko:  ${student.name} ${student.surname}",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 8.dp),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Indeks: ${student.indexNum}",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 8.dp),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Rok studiów: ${student.yearOfStudy}",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Średnia: ${student.mean}",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Navigation(allExerciseList: UsersViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.MasterScreen.route) {
        composable(route = Screens.MasterScreen.route){
            MasterScreen(
                onDetailScreen = { index ->
                    navController.navigate(Screens.DetailScreen.route + "/$index")
                },
                allExerciseList.listOfStudents
            )
        }

        composable(route = Screens.DetailScreen.route + "/{arg}"){
            val arg = it.arguments?.getString("arg")
            DetailScreen(
                arg,
                allExerciseList.listOfStudents
            )
        }
    }
}