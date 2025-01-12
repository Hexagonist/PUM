package com.example.lista_6_2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lista_6_2.ui.theme.Lista_6_2Theme


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

sealed class BottomBar(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object E1Screen : BottomBar(Screens.E1Screen.route, "Listy zadań", Icons.Default.Home)
    data object E2Screen : BottomBar(Screens.E2Screen.route, "Oceny", Icons.Default.Info)
}


@Composable
fun E1Screen(
    onE3Screen: (Any?) -> Unit,
    exerciseListList: List<ExerciseList>,
    paddingValues: PaddingValues
) {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
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
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = exerciseList.subject.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Liczba zadań: ${exerciseList.exercises.size}")
            }
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Lista: ${exerciseList.index}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Ocena: ${exerciseList.grade}")
            }
        }
    }
}

@Composable
fun E2Screen(exerciseSummaryList: List<ExerciseList>, paddingValues: PaddingValues) {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
        items(exerciseSummaryList) { exerciseSummary ->
            ExerciseSummaryCard(exerciseSummary = exerciseSummary)
        }
    }
}

@Composable
fun ExerciseSummaryCard(exerciseSummary: ExerciseList) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = exerciseSummary.subject.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Liczba list: ${exerciseSummary.listNum}")
            }
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Średnia: ${exerciseSummary.grade}")
            }
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
fun E3Screen(arg: String?, exerciseListList: List<ExerciseList>, paddingValues: PaddingValues) {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
        if (arg != null) {
            itemsIndexed(exerciseListList[arg.toInt()].exercises) { index, exercise ->
                ExerciseCard(exercise = exercise, index = index)
            }
        }
    }
}

@Composable
fun ExerciseCard(exercise: Exercise, index: Int) {
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "Pkt: ${exercise.points}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    text = "Zadanie ${index + 1}",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 8.dp),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = exercise.content,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(allExerciseList: UsersViewModel) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomMenu(navController = navController)},
        content = {paddingValues ->
            BottomNavGraph(navController = navController, allExerciseList, paddingValues) }
    )
}

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    allExerciseList: UsersViewModel,
    paddingValues: PaddingValues
){
    NavHost(navController = navController, startDestination = Screens.E1Screen.route) {
        composable(route = Screens.E1Screen.route){
            E1Screen(
                onE3Screen = { index ->
                    navController.navigate(Screens.E3Screen.route + "/$index")
                },
                allExerciseList.exerciseListList,
                paddingValues
            )
//            {navController.navigate(Screens.E3Screen.route + "/$arg")}
        }

        composable(route = Screens.E3Screen.route + "/{arg}"){
            val arg = it.arguments?.getString("arg")
            E3Screen(
                arg,
                allExerciseList.exerciseListList,
                paddingValues
            )
//            {navController.popBackStack()}
        }

        composable(route = Screens.E2Screen.route){
            E2Screen(allExerciseList.getSubjectsSummaryList(), paddingValues)
        }

    }
}

@Composable
fun BottomMenu(navController: NavHostController){
    val screens = listOf(
        BottomBar.E1Screen, BottomBar.E2Screen
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar{
        screens.forEach{screen ->
            NavigationBarItem(
                label = { Text(text = screen.title) },
                icon = { Icon(imageVector = screen.icon, contentDescription = "icon") },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {navController.navigate(screen.route)}
            )
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






