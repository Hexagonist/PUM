//package com.example.lista_6_2
//
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.Card
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import com.example.lista_6_2.ExerciseList
//
//@Composable
//fun E1Screen(navController: NavController, exerciseListList: List<ExerciseList>) {
//    LazyColumn(modifier = Modifier.fillMaxSize()) {
//        items(exerciseListList) { exerciseList ->
//            ExerciseListCard(
//                exerciseList = exerciseList,
//                onClick = {
//                    navController.navigate("e3/${exerciseList.index}")
//                }
//            )
//        }
//    }
//}
//
//@Composable
//fun ExerciseListCard(exerciseList: ExerciseList, onClick: () -> Unit) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp)
//            .clickable { onClick() }
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            Text(text = "Subject: ${exerciseList.subject.name}")
//            Text(text = "Lista: ${exerciseList.index}")
//            Text(text = "Liczba zadań: ${exerciseList.exercises.size}")
//            Text(text = "Ocena: ${exerciseList.grade}")
//        }
//    }
//}
