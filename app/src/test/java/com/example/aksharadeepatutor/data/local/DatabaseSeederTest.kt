package com.example.aksharadeepatutor.data.local

import org.junit.Assert.assertEquals
import org.junit.Test

class DatabaseSeederTest {

    @Test
    fun testSeederDataCounts() {
        val subjects = DatabaseSeeder.getSubjects()
        assertEquals(3, subjects.size)

        val chapters = DatabaseSeeder.getChapters()
        assertEquals(15, chapters.size)

        val questions = DatabaseSeeder.getQuestions()
        assertEquals(150, questions.size)

        // Verify each chapter has 10 questions
        val questionsByChapter = questions.groupBy { it.chapterId }
        for (chapterId in 1..15) {
            assertEquals("Chapter $chapterId should have 10 questions", 10, questionsByChapter[chapterId]?.size)
        }
    }
}
