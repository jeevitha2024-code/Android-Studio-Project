package com.example.aksharadeepatutor.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.aksharadeepatutor.model.Question

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewScreen(
    questions: List<Question>,
    userAnswers: List<Int?>,
    onBack: () -> Unit
) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Review Answers")
                },
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            itemsIndexed(questions) { index, question ->

                ReviewItem(
                    question = question,
                    userAnswerIndex = userAnswers.getOrNull(index)
                )
            }
        }
    }
}

@Composable
fun ReviewItem(
    question: Question,
    userAnswerIndex: Int?
) {

    val isCorrect = userAnswerIndex == question.correctAnswerIndex

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor =
                if (isCorrect)
                    Color(0xFFE8F5E9)
                else
                    Color(0xFFFBE9E7)
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            // QUESTION HEADER
            Row(
                verticalAlignment = Alignment.Top
            ) {

                Icon(
                    imageVector =
                        if (isCorrect)
                            Icons.Rounded.CheckCircle
                        else
                            Icons.Rounded.Cancel,
                    contentDescription = null,
                    tint =
                        if (isCorrect)
                            Color(0xFF4CAF50)
                        else
                            Color(0xFFF44336),
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = question.text,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // OPTIONS
            question.options.forEachIndexed { index, option ->

                val isCorrectOption =
                    index == question.correctAnswerIndex

                val isSelectedOption =
                    index == userAnswerIndex

                Surface(
                    shape = MaterialTheme.shapes.small,
                    color = when {

                        isCorrectOption ->
                            Color(0xFFC8E6C9)

                        isSelectedOption && !isCorrectOption ->
                            Color(0xFFFFCDD2)

                        else ->
                            Color.Transparent
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp)
                ) {

                    Text(
                        text = "${'A' + index}. $option",
                        modifier = Modifier.padding(10.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight =
                            if (isCorrectOption || isSelectedOption)
                                FontWeight.Bold
                            else
                                FontWeight.Normal,
                        color = when {

                            isCorrectOption ->
                                Color(0xFF2E7D32)

                            isSelectedOption && !isCorrectOption ->
                                Color(0xFFC62828)

                            else ->
                                MaterialTheme.colorScheme.onSurface
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // EXPLANATION CARD
            Card(
                colors = CardDefaults.cardColors(
                    containerColor =
                        MaterialTheme.colorScheme.secondaryContainer
                )
            ) {

                Column(
                    modifier = Modifier.padding(12.dp)
                ) {

                    Text(
                        text = "Explanation",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = question.explanation,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}