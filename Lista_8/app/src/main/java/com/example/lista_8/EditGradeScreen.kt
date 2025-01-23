// File: app/src/main/java/com/example/lista_8/ui/EditGradeScreen.kt
package com.example.lista_8.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lista_8.Grade
import com.example.lista_8.GradeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditGradeScreen(navController: NavController, gradeViewModel: GradeViewModel, gradeId: Int) {
    val grade = gradeViewModel.allGrades.observeAsState(listOf()).value.firstOrNull { it.id == gradeId }

    var subject by remember { mutableStateOf(grade?.subject ?: "") }
    var gradeValue by remember { mutableStateOf(grade?.grade ?: "") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Edit Grade") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = subject,
                onValueChange = { subject = it },
                label = { Text("Subject") }
            )
            OutlinedTextField(
                value = gradeValue,
                onValueChange = { gradeValue = it },
                label = { Text("Grade") }
            )
            Row {
                Button(onClick = {
                    grade?.let {
                        gradeViewModel.update(it.copy(subject = subject, grade = gradeValue))
                    }
                    navController.popBackStack()
                }) {
                    Text("Save")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = {
                    grade?.let { gradeViewModel.delete(it) }
                    navController.popBackStack()
                }) {
                    Text("Delete")
                }
            }
        }
    }
}
