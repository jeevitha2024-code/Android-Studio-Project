package com.example.aksharadeepatutor.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class QuestionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val chapterId: Int,

    val questionText: String,

    val optionA: String,
    val optionB: String,
    val optionC: String,
    val optionD: String,

    val correctAnswerIndex: Int,

    val explanation: String
)