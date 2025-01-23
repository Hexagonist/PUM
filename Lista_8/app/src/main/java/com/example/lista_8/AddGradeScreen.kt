// File: app/src/main/java/com/example/lista_8/ui/AddGradeScreen.kt
package com.example.lista_8.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TopAppBar
import androidx.navigation.NavController
import com.example.lista_8.Grade
import com.example.lista_8.GradeViewModel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.ui.Modifier


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddGradeScreen(navController: NavController, gradeViewModel: GradeViewModel) {
    var subject by remember { mutableStateOf("") }
    var grade by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Add Grade") })
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
                value = grade,
                onValueChange = { grade = it },
                label = { Text("Grade") }
            )
            Button(onClick = {
                gradeViewModel.insert(Grade(subject = subject, grade = grade))
                navController.popBackStack()
            }) {
                Text("Save")
            }
        }
    }
}
