//package com.example.lista_6_2
//
//import androidx.compose.runtime.Composable
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//
//@Composable
//fun NavigationHost(viewModel: UsersViewModel) {
//    val navController = rememberNavController()
//
//    NavHost(
//        navController = navController,
//        startDestination = "e1"
//    ) {
//        composable("e1") { E1Screen(navController, viewModel.exerciseListList) }
//        composable("e2") { E2Screen(viewModel.exerciseListList) }
//        composable("e3/{exerciseList}") { backStackEntry ->
//            val exerciseList = backStackEntry.arguments?.getParcelable<ExerciseList>("exerciseList")
//            exerciseList?.let {
//                E3Screen(it)
//            }
//        }
//    }
//}
