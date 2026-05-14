package com.example.aksharadeepatutor

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.aksharadeepatutor.data.local.AppDatabase
import com.example.aksharadeepatutor.data.repository.QuizRepositoryImpl
import com.example.aksharadeepatutor.ui.navigation.NavGraph
import com.example.aksharadeepatutor.ui.theme.AksharaDeepaTutorTheme
import com.example.aksharadeepatutor.worker.DailyReminderWorker
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {

    private val notificationPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        requestNotificationPermission()

        scheduleDailyReminder()

        setContent {

            AksharaDeepaTutorTheme {

                val scope = rememberCoroutineScope()

                val database =
                    AppDatabase.getDatabase(this, scope)

                val repository =
                    QuizRepositoryImpl(database.quizDao())

                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    NavGraph(repository = repository)
                }
            }
        }
    }

    private fun requestNotificationPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            notificationPermissionLauncher.launch(
                Manifest.permission.POST_NOTIFICATIONS
            )
        }
    }

    private fun scheduleDailyReminder() {

        val workRequest =
            PeriodicWorkRequestBuilder<DailyReminderWorker>(
                1,
                TimeUnit.DAYS
            ).build()

        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork(
                "daily_reminder",
                ExistingPeriodicWorkPolicy.KEEP,
                workRequest
            )
    }
}