package com.example.aksharadeepatutor.model

data class Subject(
    val id: Int,
    val name: String,
    val totalChapters: Int = 0,
    val completedChapters: Int = 0
)

data class Chapter(
    val id: Int,
    val subjectId: Int,
    val name: String,
    val score: Int? = null,
    val totalQuestions: Int = 10,
    val lastAttemptTimestamp: Long? = null,
    val subjectName: String = ""
)
data class Question(
    val id: Int,
    val chapterId: Int,
    val text: String,
    val options: List<String>,
    val correctAnswerIndex: Int,
    val explanation: String
)
