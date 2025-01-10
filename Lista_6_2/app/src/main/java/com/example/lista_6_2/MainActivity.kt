package com.example.lista_6_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.lista_6_2.ui.theme.Lista_6_2Theme

class MainActivity : ComponentActivity() {
    private val allExerciseList: UsersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Generate data
        allExerciseList.generateExerciseLists(20)

        setContent {
            Lista_6_2Theme {
                Surface(
                    modifier = androidx.compose.ui.Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationHost(viewModel = allExerciseList)
                }
            }
        }
    }
}
