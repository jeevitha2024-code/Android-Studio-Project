package com.example.aksharadeepatutor.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_progress")
data class UserProgressEntity(
    @PrimaryKey val chapterId: Int,
    val score: Int,
    val totalQuestions: Int,
    val lastAttemptTimestamp: Long
)
