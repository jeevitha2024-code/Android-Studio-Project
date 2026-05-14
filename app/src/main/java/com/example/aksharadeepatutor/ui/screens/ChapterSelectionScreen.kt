package com.example.aksharadeepatutor.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.RadioButtonUnchecked
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.aksharadeepatutor.model.Chapter
import com.example.aksharadeepatutor.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChapterSelectionScreen(
    viewModel: MainViewModel,
    subjectId: Int,
    subjectName: String,
    onChapterClick: (Chapter) -> Unit,
    onBack: () -> Unit
) {
    val chapters by viewModel.getChapters(subjectId).collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(subjectName, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(chapters) { chapter ->
                ChapterCard(
                    chapter = chapter,
                    viewModel = viewModel,
                    onClick = { onChapterClick(chapter) }
                )
            }
        }
    }
}

@Composable
fun ChapterCard(
    chapter: Chapter,
    viewModel: MainViewModel,
    onClick: () -> Unit
) {

    val isCompleted = chapter.score != null

    // ---------------- NEW INTELLIGENCE FEATURES ----------------
    val velocity = viewModel.getVelocity(chapter)
    val strength = if (chapter.score != null)
        viewModel.getStrengthLabel(chapter.score * 10)
    else "Not Attempted"

    val cardColor = when (strength) {
        "Strong 💪" -> MaterialTheme.colorScheme.primaryContainer
        "Medium 📘" -> MaterialTheme.colorScheme.tertiaryContainer
        "Weak ⚠️" -> MaterialTheme.colorScheme.errorContainer
        else -> MaterialTheme.colorScheme.surfaceVariant
    }

    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = cardColor)
    ) {

        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // STATUS ICON
            Icon(
                imageVector = if (isCompleted)
                    Icons.Rounded.CheckCircle
                else
                    Icons.Rounded.RadioButtonUnchecked,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {

                // CHAPTER NAME
                Text(
                    text = chapter.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(4.dp))

                // SCORE
                if (isCompleted) {
                    Text(
                        text = "Score: ${chapter.score}/${chapter.totalQuestions}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                // 🔥 STRENGTH LABEL
                Text(
                    text = "Strength: $strength",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold
                )

                // ⚡ VELOCITY LABEL
                Text(
                    text = velocity,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Icon(
                Icons.Rounded.ChevronRight,
                contentDescription = null
            )
        }
    }
}