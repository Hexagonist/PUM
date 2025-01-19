package com.example.lista_8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lista_8.Subject
import com.example.lista_8.UsersViewModel
import com.example.lista_8.ui.theme.Lista_8Theme
//import com.google.mlkit.vision.segmentation.subject.Subject


class MainActivity : ComponentActivity() {
    // Exercise lists
    private val allSubjects: UsersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Generate data
        allSubjects.initializeSubjects()

        setContent {
            Lista_8Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation(allSubjects)
                }
            }
        }
    }
}

sealed class Screens(val route: String) {
    data object MainScreen : Screens("main_screen")
    data object EditScreen : Screens("edit_screen")
    data object AddNewScreen : Screens("add_new_screen")
}

//@Composable
//fun MainScreen(
//    onDetailScreen: (Any?) -> Unit,
//    studentList: List<Student>
////    paddingValues: PaddingValues
//) {
////    LazyColumn(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
//    LazyColumn{
//        itemsIndexed(studentList) { index, student ->
//            StudentListCard(
//                student = student,
//                onClick = { onDetailScreen(index) }
//            )
//        }
//    }
//}
//
//@Composable
//fun StudentListCard(student: Student, onClick: () -> Unit) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp)
//            .clickable { onClick() }
//    ) {
//        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
//            Column(modifier = Modifier.padding(16.dp)) {
//                Text(text = "Imię: ${student.name}")
//                Spacer(modifier = Modifier.height(8.dp))
//                Text(text = "Indeks: ${student.indexNum}", fontSize = 20.sp, fontWeight = FontWeight.Bold)
//            }
//            Column(modifier = Modifier.padding(16.dp)) {
//                Text(text = "Nazwisko: ${student.surname}")
//            }
//        }
//    }
//}

@Composable
fun MainScreen(
    navigateToAddNewScreen: () -> Unit,
    navigateToEditScreen: (subject: Subject) -> Unit,
    subjects: List<Subject>
) {
    val meanGrade = subjects.map { it.grade }.average()

    Column(modifier = Modifier.fillMaxSize()) {
        // Display the mean grade card
        Card(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Mean Grade: ${String.format("%.2f", meanGrade)}",
                modifier = Modifier.padding(16.dp)
            )
        }

        // Display the subjects in cards
        LazyColumn {
            items(subjects) { subject ->
                SubjectCard(subject = subject, onClick = { navigateToEditScreen(subject) })
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Red button navigating to AddNewScreen
        Button(
            onClick = navigateToAddNewScreen,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text(text = "Add New Subject", color = Color.White)
        }
    }
}

@Composable
fun SubjectCard(subject: Subject, onClick: () -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable { onClick() }) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = subject.name)
            Text(text = subject.grade.toString())
        }
    }
}

@Composable
fun AddNewScreen(
    onSubjectAdded: (subject: Subject) -> Unit,
    navigateBack: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var grade by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Subject Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = grade,
            onValueChange = { grade = it },
            label = { Text("Grade") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (name.isNotEmpty() && grade.isNotEmpty()) {
                    onSubjectAdded(Subject(name, grade.toFloat()))
                    navigateBack()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Add Subject")
        }
    }
}

@Composable
fun EditScreen(
    subject: Subject,
    onSubjectUpdated: (subject: Subject) -> Unit,
    onSubjectDeleted: () -> Unit,
    navigateBack: () -> Unit
) {
    var name by remember { mutableStateOf(subject.name) }
    var grade by remember { mutableStateOf(subject.grade.toString()) }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Subject Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = grade,
            onValueChange = { grade = it },
            label = { Text("Grade") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Button(
                onClick = {
                    if (name.isNotEmpty() && grade.isNotEmpty()) {
                        onSubjectUpdated(Subject(name, grade.toFloat()))
                        navigateBack()
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Update")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    onSubjectDeleted()
                    navigateBack()
                },
                colors = ButtonDefaults.buttonColors(contentColor = Color.Red),
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Delete", color = Color.White)
            }
        }
    }
}



//@Composable
//fun DetailScreen(exerciseSummaryList: List<Student>) {
//    LazyColumn{
//        items(exerciseSummaryList) { exerciseSummary ->
//            ExerciseSummaryCard(exerciseSummary = exerciseSummary)
//        }
//    }
//}

//@Composable
//fun ExerciseSummaryCard(exerciseSummary: ExerciseList) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp)
//    ) {
//        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
//            Column(modifier = Modifier.padding(16.dp)) {
//                Text(text = exerciseSummary.subject.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
//                Spacer(modifier = Modifier.height(8.dp))
//                Text(text = "Liczba list: ${exerciseSummary.listNum}")
//            }
//            Column(modifier = Modifier.padding(16.dp)) {
//                Text(text = "Średnia: ${exerciseSummary.grade}")
//            }
//        }
//    }
//}

//@Composable
//fun EditScreen(arg: String?, studentList: List<Student>) {
//    LazyColumn {
//        if (arg != null) {
//            item {
//                ExerciseCard(studentList[arg.toInt()])
//            }
//        }
//    }
//}
//
//@Composable
//fun ExerciseCard(student: Student) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp)
//    ) {
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//        ) {
//            Column(modifier = Modifier.fillMaxSize()) {
//                Text(
//                    text = "Imię i Nazwisko:  ${student.name} ${student.surname}",
//                    fontSize = 18.sp,
//                    modifier = Modifier.padding(bottom = 8.dp),
//                    fontWeight = FontWeight.Bold
//                )
//                Text(
//                    text = "Indeks: ${student.indexNum}",
//                    fontSize = 16.sp,
//                    modifier = Modifier.padding(bottom = 8.dp),
//                    fontWeight = FontWeight.Bold
//                )
//                Text(
//                    text = "Rok studiów: ${student.yearOfStudy}",
//                    fontSize = 16.sp,
//                    modifier = Modifier.padding(bottom = 8.dp)
//                )
//                Text(
//                    text = "Średnia: ${student.mean}",
//                    fontSize = 16.sp,
//                    modifier = Modifier.padding(bottom = 8.dp)
//                )
//            }
//        }
//    }
//}

//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun Navigation(allSubjects: UsersViewModel) {
//    val navController = rememberNavController()
//
//    NavHost(navController = navController, startDestination = Screens.MasterScreen.route) {
//        composable(route = Screens.MasterScreen.route){
//            MasterScreen(
//                onDetailScreen = { index ->
//                    navController.navigate(Screens.DetailScreen.route + "/$index")
//                },
//                allExerciseList.listOfStudents
////                paddingValues
//            )
//        }
//
//        composable(route = Screens.DetailScreen.route + "/{arg}"){
//            val arg = it.arguments?.getString("arg")
//            DetailScreen(
//                arg,
//                allExerciseList.listOfStudents
////                paddingValues
//            )
//        }
//    }
//}

@Composable
fun Navigation(
    subjectViewModel: UsersViewModel
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.MainScreen.route) {
        composable(Screens.MainScreen.route) {
            MainScreen(
                navigateToAddNewScreen = { navController.navigate(Screens.AddNewScreen.route) },
                navigateToEditScreen = { subject ->
                    navController.navigate("${Screens.EditScreen.route}/${subject.name}")
                },
                subjects = subjectViewModel.subjectList
            )
        }
        composable(Screens.AddNewScreen.route) {
            AddNewScreen(
                onSubjectAdded = { subjectViewModel.addSubject(it) },
                navigateBack = { navController.popBackStack() }
            )
        }
        composable(
            route = "${Screens.EditScreen.route}/{subjectName}",
            arguments = listOf(navArgument("subjectName") { type = NavType.StringType })
        ) { backStackEntry ->
            val subjectName = backStackEntry.arguments?.getString("subjectName") ?: return@composable
            val subject = subjectViewModel.subjectList.find { it.name == subjectName }
            subject?.let {
                EditScreen(
                    subject = it,
                    onSubjectUpdated = { updatedSubject -> subjectViewModel.updateSubject(it, updatedSubject) },
                    onSubjectDeleted = { subjectViewModel.deleteSubject(it) },
                    navigateBack = { navController.popBackStack() }
                )
            }
        }
    }
}
