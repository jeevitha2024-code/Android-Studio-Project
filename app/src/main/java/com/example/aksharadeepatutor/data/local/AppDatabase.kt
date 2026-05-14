package com.example.aksharadeepatutor.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.aksharadeepatutor.data.local.dao.QuizDao
import com.example.aksharadeepatutor.data.local.entities.ChapterEntity
import com.example.aksharadeepatutor.data.local.entities.QuestionEntity
import com.example.aksharadeepatutor.data.local.entities.SubjectEntity
import com.example.aksharadeepatutor.data.local.entities.UserProgressEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        SubjectEntity::class,
        ChapterEntity::class,
        QuestionEntity::class,
        UserProgressEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun quizDao(): QuizDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "akshara_deepa_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(AppDatabaseCallback(scope))
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }

    private class AppDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    val quizDao = database.quizDao()

                    quizDao.insertSubjects(DatabaseSeeder.getSubjects())
                    quizDao.insertChapters(DatabaseSeeder.getChapters())
                    quizDao.insertQuestions(DatabaseSeeder.getQuestions())
                }
            }
        }
    }
}