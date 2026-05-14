package com.example.aksharadeepatutor.worker

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.aksharadeepatutor.R

class DailyReminderWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    override fun doWork(): Result {

        createNotificationChannel()

        val builder = NotificationCompat.Builder(
            applicationContext,
            "daily_reminder_channel"
        )
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Akshara Deepa Tutor")
            .setContentText("Complete at least 1 topic today 📘")
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        if (
            ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return Result.success()
        }

        NotificationManagerCompat.from(applicationContext)
            .notify(1001, builder.build())

        return Result.success()
    }

    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(
                "daily_reminder_channel",
                "Daily Reminder",
                NotificationManager.IMPORTANCE_HIGH
            )

            val manager =
                applicationContext.getSystemService(
                    Context.NOTIFICATION_SERVICE
                ) as NotificationManager

            manager.createNotificationChannel(channel)
        }
    }
}