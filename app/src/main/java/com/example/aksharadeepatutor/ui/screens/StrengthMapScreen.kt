package com.example.aksharadeepatutor.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.aksharadeepatutor.model.Subject
import com.example.aksharadeepatutor.ui.components.RadarChartView
import com.example.aksharadeepatutor.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StrengthMapScreen(
    viewModel: MainViewModel,
    onBack: () -> Unit
) {

    val subjects by viewModel.subjects.collectAsState()
    val chapterAccuracy by viewModel.chapterAccuracy.collectAsState()

    // ---------------- SAFE RADAR VALUES ----------------
    val mathProgress = chapterAccuracy.filterKeys { it in 1..5 }
        .averageValues()

    val scienceProgress = chapterAccuracy.filterKeys { it in 6..10 }
        .averageValues()

    val socialProgress = chapterAccuracy.filterKeys { it in 11..15 }
        .averageValues()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Strength Map", fontWeight = FontWeight.Bold)
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = null)
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
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            item {
                Card(modifier = Modifier.fillMaxWidth()) {

                    Column(modifier = Modifier.padding(16.dp)) {

                        Text(
                            "Subject Mastery Radar",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        RadarChartView(
                            mathScore = mathProgress,
                            scienceScore = scienceProgress,
                            socialScore = socialProgress,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }

            items(subjects) { subject ->
                SubjectStrengthCard(subject, chapterAccuracy)
            }

            item {
                LearningTipsSection(subjects)
            }
        }
    }
}

@Composable
fun SubjectStrengthCard(subject: Subject, chapterAccuracy: Map<Int, Int>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = subject.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${subject.completedChapters}/${subject.totalChapters} Chapters",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            val subjectChapters = when (subject.id) {
                1 -> 1..5
                2 -> 6..10
                3 -> 11..15
                else -> IntRange.EMPTY
            }

            val avgAccuracy = chapterAccuracy.filterKeys { it in subjectChapters }.averageValues()

            Spacer(modifier = Modifier.height(12.dp))

            LinearProgressIndicator(
                progress = { avgAccuracy / 100f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp),
                strokeCap = StrokeCap.Round
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Subject Mastery: ${avgAccuracy.toInt()}%",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun LearningTipsSection(subjects: List<Subject>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f)
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                "Learning Tips",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

            val lowMasterySubjects = subjects.filter { it.completedChapters < it.totalChapters / 2 }

            if (lowMasterySubjects.isNotEmpty()) {
                BulletPoint("Focus on ${lowMasterySubjects.joinToString { it.name }} where you have more chapters to complete.")
            }

            BulletPoint("Review chapters with less than 60% accuracy.")
            BulletPoint("Practice Daily to maintain your streaks.")
            BulletPoint("Focus on your weak areas shown in the radar chart.")
        }
    }
}

@Composable
fun BulletPoint(text: String) {
    Row(modifier = Modifier.padding(vertical = 4.dp)) {
        Text("• ", fontWeight = FontWeight.Bold)
        Text(text, style = MaterialTheme.typography.bodyMedium)
    }
}

/* ---------------- SAFE EXTENSION FUNCTION ---------------- */

fun Map<Int, Int>.averageValues(): Float {
    return if (this.isNotEmpty()) {
        this.values.average().toFloat()
    } else 0f
}
