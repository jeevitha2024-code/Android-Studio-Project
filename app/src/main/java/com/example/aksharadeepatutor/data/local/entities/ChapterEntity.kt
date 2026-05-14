package com.example.aksharadeepatutor.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "chapters",
    foreignKeys = [
        ForeignKey(
            entity = SubjectEntity::class,
            parentColumns = ["id"],
            childColumns = ["subjectId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ChapterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val subjectId: Int,
    val name: String
)
