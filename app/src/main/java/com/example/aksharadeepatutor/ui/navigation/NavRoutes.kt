package com.example.aksharadeepatutor.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed class NavRoute : NavKey {
    @Serializable
    data object Home : NavRoute()
    
    @Serializable
    data object SubjectSelection : NavRoute()
    
    @Serializable
    data class ChapterSelection(val subjectId: Int, val subjectName: String) : NavRoute()
    
    @Serializable
    data class Quiz(val chapterId: Int, val chapterName: String) : NavRoute()
    
    @Serializable
    data class Results(val score: Int, val total: Int, val chapterId: Int) : NavRoute()
    @Serializable
    data object StrengthMap : NavRoute()

    @Serializable
    data class Review(val chapterId: Int) : NavRoute()
}
