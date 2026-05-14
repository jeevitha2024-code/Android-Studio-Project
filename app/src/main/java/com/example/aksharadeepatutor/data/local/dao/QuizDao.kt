package com.example.aksharadeepatutor.data.local.dao

import androidx.room.*
import com.example.aksharadeepatutor.data.local.entities.*
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizDao {

    @Query("SELECT * FROM subjects")
    fun getAllSubjects(): Flow<List<SubjectEntity>>

    @Query("SELECT * FROM chapters WHERE subjectId = :subjectId")
    fun getChaptersBySubject(subjectId: Int): Flow<List<ChapterEntity>>

    @Query("SELECT * FROM chapters")
    fun getAllChapters(): Flow<List<ChapterEntity>>

    @Query("SELECT * FROM questions WHERE chapterId = :chapterId")
    fun getQuestionsByChapter(chapterId: Int): Flow<List<QuestionEntity>>

    @Query("SELECT * FROM user_progress WHERE chapterId = :chapterId")
    suspend fun getProgressForChapter(chapterId: Int): UserProgressEntity?

    @Query("SELECT * FROM user_progress")
    fun getAllProgress(): Flow<List<UserProgressEntity>>

    // ---------------- PROGRESS INSERT ----------------
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProgress(progress: UserProgressEntity)

    // ---------------- PROGRESS UPDATE ----------------
    @Query("""
        UPDATE user_progress 
        SET score = :score,
            totalQuestions = :totalQuestions,
            lastAttemptTimestamp = :time
        WHERE chapterId = :chapterId
    """)
    suspend fun updateProgress(
        chapterId: Int,
        score: Int,
        totalQuestions: Int,
        time: Long
    )

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSubjects(subjects: List<SubjectEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertChapters(chapters: List<ChapterEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertQuestions(questions: List<QuestionEntity>)
}