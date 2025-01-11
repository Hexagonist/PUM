package com.example.lista_6_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lista_6_2.ui.theme.Lista_6_2Theme

//class MainActivity : ComponentActivity() {
//    private val allExerciseList: UsersViewModel by viewModels()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        // Generate data
//        allExerciseList.generateExerciseLists(20)
//
//        setContent {
//            Lista_6_2Theme {
//                Surface(
//                    modifier = androidx.compose.ui.Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    NavigationHost(viewModel = allExerciseList)
//                }
//            }
//        }
//    }
//}

class MainActivity : ComponentActivity() {
    // Exercise lists
    private val allExerciseList: UsersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Generate data
        allExerciseList.generateExerciseLists(20)

        setContent {
            Lista_6_2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    Navigation()
                    Navigation(allExerciseList)
                }
            }
        }
    }
}
//
//sealed class Screens(val route: String) {
//    data object MainScreen : Screens("main_screen")
//    data object SecondScreen : Screens("second_screen")
//}

sealed class Screens(val route: String) {
    data object E1Screen : Screens("e1_screen")
    data object E2Screen : Screens("e2_screen")
    data object E3Screen : Screens("e3_screen")
}

@Composable
fun E1Screen(onE3Screen: (Any?) -> Unit, exerciseListList: List<ExerciseList>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(exerciseListList) { exerciseList ->
            ExerciseListCard(
                exerciseList = exerciseList,
//                onClick = {
////                    navController.navigate("e3/${exerciseList.index}")
//                    onE3Screen
//                }
                onClick = { onE3Screen(exerciseList.index) }
            )
        }
    }
}

@Composable
fun ExerciseListCard(exerciseList: ExerciseList, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Subject: ${exerciseList.subject.name}")
            Text(text = "Lista: ${exerciseList.index}")
            Text(text = "Liczba zadań: ${exerciseList.exercises.size}")
            Text(text = "Ocena: ${exerciseList.grade}")
        }
    }
}

//@Composable
//fun E2Screen(exerciseSummaryList: List<ExerciseList>) {
//    LazyColumn(modifier = Modifier.fillMaxSize()) {
//        items(exerciseSummaryList) { exerciseSummary ->
//            ExerciseSummaryCard(exerciseSummary = exerciseSummary)
//        }
//    }
//}

@Composable
fun ExerciseSummaryCard(exerciseSummary: ExerciseList) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Subject: ${exerciseSummary.subject.name}")
            Text(text = "Średnia: ${exerciseSummary.grade}")
            Text(text = "Liczba list: ${exerciseSummary.listNum}")
        }
    }
}

//@Composable
//fun E3Screen(exerciseList: ExerciseList) {
//    LazyColumn(modifier = Modifier.fillMaxSize()) {
//        items(exerciseList.exercises) { exercise ->
//            ExerciseCard(exercise = exercise)
//        }
//    }
//}

@Composable
fun E3Screen(arg: String?, exerciseListList: List<ExerciseList>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        if (arg != null) {
            items(exerciseListList[arg.toInt()].exercises) { exercise ->
                ExerciseCard(exercise = exercise)
            }
        }
    }
}

@Composable
fun ExerciseCard(exercise: Exercise) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Zadanie: ${exercise.content}")
            Text(text = "Punkty: ${exercise.points}")
        }
    }
}


//@Composable
//fun Navigation() {
//    val navController = rememberNavController()
//    NavHost(navController = navController, startDestination = Screens.MainScreen.route) {
//        composable(route = Screens.MainScreen.route){
//            val arg = 5
//            MainScreen{navController.navigate(Screens.SecondScreen.route + "/$arg")}
//        }
//        composable(route = Screens.SecondScreen.route + "/{arg}"){
//            val arg = it.arguments?.getString("arg")
//            SecondScreen(arg) {navController.popBackStack()}
//        }
//    }
//}

@Composable
fun Navigation(allExerciseList: UsersViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.E1Screen.route) {
        composable(route = Screens.E1Screen.route){
            E1Screen(
                onE3Screen = { index ->
                    navController.navigate(Screens.E3Screen.route + "/$index")
                },
                allExerciseList.exerciseListList
            )
//            {navController.navigate(Screens.E3Screen.route + "/$arg")}
        }

        composable(route = Screens.E3Screen.route + "/{arg}"){
            val arg = it.arguments?.getString("arg")
            E3Screen(
                arg,
                allExerciseList.exerciseListList
            )
//            {navController.popBackStack()}
        }
    }
}


//
//@Composable
//fun MainScreen(onSecondScreen: () -> Unit) {
//    Column(
//        Modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text("Home Screen")
//        Spacer(modifier = Modifier.height(8.dp))
//        Button(onClick = onSecondScreen) {
//            Text("Go to Second Screen")
//        }
//    }
//}
//
//@Composable
//fun SecondScreen(arg: String?, onHome: () -> Unit) {
//    Column(
//        Modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text("Second Screen. Argument: $arg")
//        Spacer(modifier = Modifier.height(8.dp))
//        Button(onClick = onHome) { Text("Go back to Main Screen") }
//    }
//}




