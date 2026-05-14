package com.example.aksharadeepatutor.data.repository

import com.example.aksharadeepatutor.model.*
import kotlinx.coroutines.flow.Flow

interface QuizRepository {

    fun getSubjects(): Flow<List<Subject>>

    fun getChapters(subjectId: Int): Flow<List<Chapter>>

    fun getQuestions(chapterId: Int): Flow<List<Question>>

    suspend fun saveProgress(
        chapterId: Int,
        correct: Int,
        totalQuestions: Int,
        accuracy: Int
    )

    fun getAllProgress(): Flow<Map<Int, Int>>
}