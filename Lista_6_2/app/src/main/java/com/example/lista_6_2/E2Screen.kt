package com.example.lista_6_2

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lista_6_2.ExerciseList

//@Composable
//fun E2Screen(exerciseSummaryList: List<ExerciseList>) {
//    LazyColumn(modifier = Modifier.fillMaxSize()) {
//        items(exerciseSummaryList) { exerciseSummary ->
//            ExerciseSummaryCard(exerciseSummary = exerciseSummary)
//        }
//    }
//}
//
//@Composable
//fun ExerciseSummaryCard(exerciseSummary: ExerciseList) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp)
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            Text(text = "Subject: ${exerciseSummary.subject.name}")
//            Text(text = "Średnia: ${exerciseSummary.grade}")
//            Text(text = "Liczba list: ${exerciseSummary.listNum}")
//        }
//    }
//}
