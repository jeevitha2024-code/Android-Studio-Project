package com.example.aksharadeepatutor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.aksharadeepatutor.data.local.AppDatabase
import com.example.aksharadeepatutor.data.repository.QuizRepositoryImpl
import com.example.aksharadeepatutor.ui.navigation.NavGraph
import com.example.aksharadeepatutor.ui.theme.AksharaDeepaTutorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AksharaDeepaTutorTheme {
                val scope = rememberCoroutineScope()
                val database = AppDatabase.getDatabase(this, scope)
                val repository = QuizRepositoryImpl(database.quizDao())

                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    NavGraph(repository = repository)
                }
            }
        }
    }
}
