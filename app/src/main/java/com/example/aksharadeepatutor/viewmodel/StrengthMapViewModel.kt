package com.example.aksharadeepatutor.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aksharadeepatutor.data.repository.QuizRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class StrengthMapViewModel(
    private val repository: QuizRepository
) : ViewModel() {

    // This gives you chapter-wise ACCURACY map
    val chapterAccuracy: StateFlow<Map<Int, Int>> =
        repository.getAllProgress()
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyMap()
            )
}