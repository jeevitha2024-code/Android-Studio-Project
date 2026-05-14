package com.example.aksharadeepatutor.data.repository

import com.example.aksharadeepatutor.data.local.dao.QuizDao
import com.example.aksharadeepatutor.data.local.entities.UserProgressEntity
import com.example.aksharadeepatutor.model.Chapter
import com.example.aksharadeepatutor.model.Question
import com.example.aksharadeepatutor.model.Subject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class QuizRepositoryImpl(private val quizDao: QuizDao) : QuizRepository {

    // ---------------- SUBJECTS ----------------
    override fun getSubjects(): Flow<List<Subject>> {
        return combine(
            quizDao.getAllSubjects(),
            quizDao.getAllChapters(),
            quizDao.getAllProgress()
        ) { subjects, chapters, progressList ->

            val progressMap = progressList.associateBy { it.chapterId }
            val chaptersBySubject = chapters.groupBy { it.subjectId }

            subjects.map { subjectEntity ->
                val subjectChapters = chaptersBySubject[subjectEntity.id] ?: emptyList()
                val completedCount = subjectChapters.count { progressMap.containsKey(it.id) }

                Subject(
                    id = subjectEntity.id,
                    name = subjectEntity.name,
                    totalChapters = subjectChapters.size,
                    completedChapters = completedCount
                )
            }
        }
    }

    // ---------------- CHAPTERS ----------------
    override fun getChapters(subjectId: Int): Flow<List<Chapter>> {
        return combine(
            quizDao.getChaptersBySubject(subjectId),
            quizDao.getAllProgress()
        ) { chapters, progressList ->

            val progressMap = progressList.associateBy { it.chapterId }

            chapters.map { chapterEntity ->
                val progress = progressMap[chapterEntity.id]

                Chapter(
                    id = chapterEntity.id,
                    subjectId = chapterEntity.subjectId,
                    name = chapterEntity.name,
                    score = progress?.score,
                    totalQuestions = progress?.totalQuestions ?: 10,
                    lastAttemptTimestamp = progress?.lastAttemptTimestamp,
                    subjectName = when (chapterEntity.subjectId) {
                        1 -> "Mathematics"
                        2 -> "Science"
                        3 -> "Social Science"
                        else -> "Unknown"
                    }
                )
            }
        }
    }

    // ---------------- QUESTIONS ----------------
    override fun getQuestions(chapterId: Int): Flow<List<Question>> {
        return quizDao.getQuestionsByChapter(chapterId).map { questions ->

            questions.map { entity ->
                Question(
                    id = entity.id,
                    chapterId = entity.chapterId,
                    text = entity.questionText,
                    options = listOf(
                        entity.optionA,
                        entity.optionB,
                        entity.optionC,
                        entity.optionD
                    ),
                    correctAnswerIndex = entity.correctAnswerIndex,
                    explanation = entity.explanation
                )
            }
        }
    }

    // ---------------- SAVE PROGRESS (FIXED) ----------------
    override suspend fun saveProgress(
        chapterId: Int,
        correct: Int,
        totalQuestions: Int,
        accuracy: Int
    ) {
        val progress = UserProgressEntity(
            chapterId = chapterId,
            score = correct,
            totalQuestions = totalQuestions,
            lastAttemptTimestamp = System.currentTimeMillis()
        )

        val existing = quizDao.getProgressForChapter(chapterId)

        if (existing == null) {
            quizDao.insertProgress(progress)
        } else {
            quizDao.updateProgress(
                chapterId = chapterId,
                score = correct,
                totalQuestions = totalQuestions,
                time = System.currentTimeMillis()
            )
        }
    }

    // ---------------- STRATEGIC FIX: ACCURACY MAP ----------------
    override fun getAllProgress(): Flow<Map<Int, Int>> {
        return quizDao.getAllProgress().map { list ->

            list.associate { progress ->

                val accuracy = if (progress.totalQuestions > 0) {
                    (progress.score * 100) / progress.totalQuestions
                } else 0

                progress.chapterId to accuracy
            }
        }
    }
}