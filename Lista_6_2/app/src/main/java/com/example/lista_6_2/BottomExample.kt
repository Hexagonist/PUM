//package com.example.lista_6_2
//
//import android.annotation.SuppressLint
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Email
//import androidx.compose.material.icons.filled.Home
//import androidx.compose.material.icons.filled.Info
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.NavigationBar
//import androidx.compose.material3.NavigationBarItem
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavDestination.Companion.hierarchy
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.currentBackStackEntryAsState
//import androidx.navigation.compose.rememberNavController
//
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//@Composable
//fun HomeScreen(){
//    Box(
//        Modifier
//            .fillMaxSize()
//            .background(Color.Cyan),
//        contentAlignment = Alignment.Center
//    ) {
//        Text(
//            text = "Home Screen",
//            fontSize = 40.sp
//        )
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun HomePreview() {
////    JetpackComposeBottomNavigationBasicsTheme {
////        HomeScreen()
////    }
//}
//
//@Composable
//fun FirstScreen(){
//    Box(
//        Modifier
//            .fillMaxSize()
//            .background(Color.LightGray),
//        contentAlignment = Alignment.Center
//    ) {
//        Text(
//            text = "First Screen",
//            fontSize = 40.sp
//        )
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun FirstPreview() {
////    JetpackComposeBottomNavigationBasicsTheme {
////        FirstScreen()
////    }
//}
//
//@Composable
//fun SecondScreen(){
//    Box(
//        Modifier
//            .fillMaxSize()
//            .background(Color.Green),
//        contentAlignment = Alignment.Center
//    ) {
//        Text(
//            text = "Second Screen",
//            fontSize = 40.sp
//        )
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun SecondPreview() {
////    JetpackComposeBottomNavigationBasicsTheme {
////        SecondScreen()
////    }
//}
//
//////////////////////////////////////////////////////////////
//sealed class Screens(val route: String) {
//    data object HomeScreen : Screens("home")
//    data object FirstScreen : Screens("first")
//    data object SecondScreen : Screens("second")
//}
//
//sealed class BottomBar(
//    val route: String,
//    val title: String,
//    val icon: ImageVector
//) {
//    data object Home : BottomBar(Screens.HomeScreen.route, "Home", Icons.Default.Home)
//    data object First : BottomBar(Screens.FirstScreen.route, "First", Icons.Default.Info)
//    data object Second : BottomBar(Screens.SecondScreen.route, "Second", Icons.Default.Email)
//}
//
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun Navigation(){
//    val navController = rememberNavController()
//    Scaffold(
//        bottomBar = { BottomMenu(navController = navController)},
//        content = { BottomNavGraph(navController = navController) }
//    )
//}
//
//@Composable
//fun BottomNavGraph(navController: NavHostController){
//    NavHost(
//        navController = navController,
//        startDestination = Screens.HomeScreen.route
//    ) {
//        composable(route = Screens.HomeScreen.route){ HomeScreen() }
//        composable(route = Screens.FirstScreen.route){ FirstScreen() }
//        composable(route = Screens.SecondScreen.route){ SecondScreen() }
//    }
//}
//
//@Composable
//fun BottomMenu(navController: NavHostController){
//    val screens = listOf(
//        BottomBar.Home, BottomBar.First, BottomBar.Second
//    )
//
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentDestination = navBackStackEntry?.destination
//
//    NavigationBar{
//        screens.forEach{screen ->
//            NavigationBarItem(
//                label = { Text(text = screen.title) },
//                icon = { Icon(imageVector = screen.icon, contentDescription = "icon") },
//                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
//                onClick = {navController.navigate(screen.route)}
//            )
//        }
//    }
//}
