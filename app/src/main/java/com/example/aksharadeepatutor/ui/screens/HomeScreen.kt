package com.example.aksharadeepatutor.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.aksharadeepatutor.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    onStartLearning: () -> Unit,
    onViewStrengthMap: () -> Unit
) {

    val subjects by viewModel.subjects.collectAsState()

    val totalChapters = subjects.sumOf { it.totalChapters }

    val completedChapters =
        subjects.sumOf { it.completedChapters }

    val progress =
        if (totalChapters > 0)
            completedChapters.toFloat() / totalChapters
        else
            0f

    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        "Akshara Deepa Tutor",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor =
                        MaterialTheme.colorScheme.background
                )
            )
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // PROGRESS CARD
            item {

                ProgressCard(
                    progress = progress,
                    completed = completedChapters,
                    total = totalChapters
                )
            }

            // WELCOME CARD
            item {
                WelcomeCard()
            }

            // DAILY GOAL
            item {
                DailyGoalCard()
            }

            // ACTION BUTTONS
            item {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement =
                        Arrangement.spacedBy(16.dp)
                ) {

                    ActionCard(
                        title = "Start Learning",
                        icon = Icons.Rounded.PlayArrow,
                        modifier = Modifier.weight(1f),
                        containerColor =
                            MaterialTheme.colorScheme.primaryContainer,
                        onClick = onStartLearning
                    )

                    ActionCard(
                        title = "Strength Map",
                        icon = Icons.Rounded.Insights,
                        modifier = Modifier.weight(1f),
                        containerColor =
                            MaterialTheme.colorScheme.secondaryContainer,
                        onClick = onViewStrengthMap
                    )
                }
            }

            // RECENT ACTIVITY
            item {

                val recentSubjects =
                    subjects.filter {
                        it.completedChapters > 0
                    }

                if (recentSubjects.isNotEmpty()) {

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor =
                                MaterialTheme.colorScheme.surfaceVariant
                        )
                    ) {

                        Column(
                            modifier = Modifier.padding(20.dp)
                        ) {

                            Text(
                                text = "Recent Activity",
                                style =
                                    MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(
                                modifier = Modifier.height(12.dp)
                            )

                            recentSubjects.forEach { subject ->

                                Text(
                                    text =
                                        "✓ ${subject.name} : ${subject.completedChapters} chapters completed",
                                    style =
                                        MaterialTheme.typography.bodyLarge,
                                    modifier =
                                        Modifier.padding(vertical = 6.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProgressCard(
    progress: Float,
    completed: Int,
    total: Int
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor =
                MaterialTheme.colorScheme.tertiaryContainer
        )
    ) {

        Column(
            modifier = Modifier.padding(24.dp)
        ) {

            Text(
                "Daily Learning Progress",
                style = MaterialTheme.typography.titleMedium,
                color =
                    MaterialTheme.colorScheme.onTertiaryContainer
            )

            Spacer(modifier = Modifier.height(16.dp))

            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(12.dp),
                strokeCap = StrokeCap.Round,
                color = MaterialTheme.colorScheme.tertiary,
                trackColor =
                    MaterialTheme.colorScheme
                        .onTertiaryContainer
                        .copy(alpha = 0.1f)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                "$completed / $total Chapters Completed",
                style = MaterialTheme.typography.bodyMedium,
                color =
                    MaterialTheme.colorScheme.onTertiaryContainer
            )
        }
    }
}

@Composable
fun WelcomeCard() {

    val messages = listOf(
        "Keep going — small steps lead to big success!",
        "Consistency is the key to mastering SSLC!",
        "Every chapter you finish brings you closer to success!",
        "Smart learning today, bright future tomorrow!"
    )

    val message = messages.random()

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor =
                MaterialTheme.colorScheme.primaryContainer
        )
    ) {

        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            Text(
                text = "Welcome, Student 👋",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = message,
                style = MaterialTheme.typography.bodyLarge,
                color =
                    MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Composable
fun DailyGoalCard() {

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor =
                MaterialTheme.colorScheme.secondaryContainer
        )
    ) {

        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Rounded.EmojiEvents,
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {

                Text(
                    text = "Daily Goal",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text =
                        "Complete at least 1 topic today 📘",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun ActionCard(
    title: String,
    icon: ImageVector,
    containerColor: androidx.compose.ui.graphics.Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Card(
        onClick = onClick,
        modifier = modifier.height(120.dp),
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement =
                Arrangement.Center,
            horizontalAlignment =
                Alignment.CenterHorizontally
        ) {

            Icon(
                icon,
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                title,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}