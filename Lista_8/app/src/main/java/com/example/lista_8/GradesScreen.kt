// File: app/src/main/java/com/example/lista_8/ui/GradesScreen.kt
package com.example.lista_8.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lista_8.Grade
import com.example.lista_8.GradeViewModel
import com.example.lista_8.GradeViewModelFactory
//import com.example.lista_8.Lista8App

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GradesScreen(navController: NavController, gradeViewModel: GradeViewModel) {
    val grades = gradeViewModel.allGrades.observeAsState(listOf())

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Lista 8 - Grades") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("add") }) {
                Text("+")
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(grades.value) { grade ->
                GradeRow(grade, onEdit = {
                    navController.navigate("edit/${grade.id}")
                })
            }
        }
    }
}

@Composable
fun GradeRow(grade: Grade, onEdit: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onEdit() },
//        elevation = 4.dp
    ) {
        Column(Modifier.padding(16.dp)) {
            Text("Subject: ${grade.subject}")
            Text("Grade: ${grade.grade}")
        }
    }
}
