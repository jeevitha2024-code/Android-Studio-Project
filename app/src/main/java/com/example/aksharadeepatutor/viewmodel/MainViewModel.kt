package com.example.aksharadeepatutor.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aksharadeepatutor.data.repository.QuizRepository
import com.example.aksharadeepatutor.model.Chapter
import com.example.aksharadeepatutor.model.Subject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class MainViewModel(private val repository: QuizRepository) : ViewModel() {

    val subjects: StateFlow<List<Subject>> =
        repository.getSubjects()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val chapterAccuracy: StateFlow<Map<Int, Int>> =
        repository.getAllProgress()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyMap())

    fun getChapters(subjectId: Int): StateFlow<List<Chapter>> =
        repository.getChapters(subjectId)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // ---------------- NEW FEATURE 1: STRENGTH LABEL ----------------
    fun getStrengthLabel(score: Int): String {
        return when {
            score >= 75 -> "Strong 💪"
            score >= 40 -> "Medium 📘"
            else -> "Weak ⚠️"
        }
    }

    // ---------------- NEW FEATURE 2: CHAPTER STATUS ----------------
    fun getChapterStatus(chapter: Chapter): String {
        val score = chapter.score ?: return "Not Attempted"
        return getStrengthLabel(score * 10) // assuming score is small-scale
    }

    // ---------------- NEW FEATURE 3: PROGRESS VELOCITY ----------------
    fun getVelocity(chapter: Chapter): String {
        val lastTime = chapter.lastAttemptTimestamp ?: return "New Chapter 📘"

        val days =
            (System.currentTimeMillis() - lastTime) /
                    (1000 * 60 * 60 * 24)

        return when {
            days <= 1 -> "Fast Learner ⚡"
            days <= 3 -> "Steady Progress 📈"
            else -> "Needs Revision 🎯"
        }
    }

    // ---------------- NEW FEATURE 4: SUBJECT STRENGTH ----------------
    fun getSubjectStrength(subject: Subject): String {
        val progress =
            if (subject.totalChapters > 0)
                (subject.completedChapters.toFloat() / subject.totalChapters) * 100
            else 0f

        return getStrengthLabel(progress.toInt())
    }
}