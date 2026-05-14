package com.example.aksharadeepatutor.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Timer
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.aksharadeepatutor.viewmodel.QuizViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    viewModel: QuizViewModel,
    chapterName: String,
    onExit: () -> Unit,
    onFinished: (Int, Int) -> Unit
) {

    val state by viewModel.uiState.collectAsState()

    // Navigate to Result Screen
    LaunchedEffect(state.isQuizFinished) {
        if (state.isQuizFinished) {
            onFinished(state.score, state.questions.size)
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = chapterName,
                        fontWeight = FontWeight.Bold
                    )
                },

                navigationIcon = {
                    IconButton(onClick = onExit) {
                        Icon(
                            Icons.Rounded.Close,
                            contentDescription = "Exit"
                        )
                    }
                },

                actions = {
                    TimerDisplay(timeLeft = state.timeLeft)
                }
            )
        }
    ) { padding ->

        // LOADING
        if (state.questions.isEmpty()) {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        // QUIZ CONTENT
        else if (state.currentQuestionIndex < state.questions.size) {

            val question =
                state.questions[state.currentQuestionIndex]

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {

                // PROGRESS BAR
                LinearProgressIndicator(
                    progress = {
                        (state.currentQuestionIndex + 1).toFloat() /
                                state.questions.size
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp),

                    strokeCap = StrokeCap.Round
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Question ${state.currentQuestionIndex + 1} of ${state.questions.size}",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(24.dp))

                // QUESTION
                Card(
                    modifier = Modifier.fillMaxWidth(),

                    colors = CardDefaults.cardColors(
                        containerColor =
                            MaterialTheme.colorScheme.primaryContainer
                    )
                ) {

                    Text(
                        text = question.text,

                        style = MaterialTheme.typography.headlineSmall,

                        fontWeight = FontWeight.Bold,

                        modifier = Modifier.padding(20.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // OPTIONS
                LazyColumn(
                    modifier = Modifier.weight(1f),

                    verticalArrangement =
                        Arrangement.spacedBy(12.dp)
                ) {

                    itemsIndexed(question.options) { index, option ->

                        OptionItem(
                            text = option,

                            isSelected =
                                state.selectedOptionIndex == index,

                            isCorrect =
                                question.correctAnswerIndex == index,

                            isAnswerConfirmed =
                                state.isAnswerConfirmed,

                            onClick = {
                                viewModel.onOptionSelected(index)
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // NEXT BUTTON
                Button(
                    onClick = {

                        if (state.currentQuestionIndex ==
                            state.questions.lastIndex
                        ) {
                            viewModel.submitQuiz()
                        } else {
                            viewModel.goToNextQuestion()
                        }
                    },

                    modifier = Modifier.fillMaxWidth(),

                    enabled = state.isAnswerConfirmed,

                    contentPadding =
                        PaddingValues(vertical = 14.dp)
                ) {

                    Text(
                        text =
                            if (state.currentQuestionIndex ==
                                state.questions.lastIndex
                            ) {
                                "Submit Quiz"
                            } else {
                                "Next Question"
                            }
                    )
                }
            }
        }
    }
}

@Composable
fun TimerDisplay(timeLeft: Int) {

    Surface(
        color =
            if (timeLeft <= 10)
                MaterialTheme.colorScheme.errorContainer
            else
                MaterialTheme.colorScheme.secondaryContainer,

        shape = RoundedCornerShape(20.dp),

        modifier = Modifier.padding(end = 12.dp)
    ) {

        Row(
            modifier = Modifier.padding(
                horizontal = 12.dp,
                vertical = 6.dp
            ),

            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                Icons.Rounded.Timer,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = "${timeLeft}s",
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun OptionItem(
    text: String,
    isSelected: Boolean,
    isCorrect: Boolean,
    isAnswerConfirmed: Boolean,
    onClick: () -> Unit
) {

    val containerColor = when {

        isAnswerConfirmed && isCorrect ->
            Color(0xFFC8E6C9)

        isAnswerConfirmed &&
                isSelected &&
                !isCorrect ->
            Color(0xFFFFCDD2)

        isSelected ->
            MaterialTheme.colorScheme.primaryContainer

        else ->
            MaterialTheme.colorScheme.surface
    }

    val borderColor = when {

        isAnswerConfirmed && isCorrect ->
            Color(0xFF4CAF50)

        isAnswerConfirmed &&
                isSelected &&
                !isCorrect ->
            Color(0xFFF44336)

        isSelected ->
            MaterialTheme.colorScheme.primary

        else ->
            MaterialTheme.colorScheme.outlineVariant
    }

    OutlinedCard(
        onClick = onClick,

        enabled = !isAnswerConfirmed,

        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(16.dp),

        colors = CardDefaults.outlinedCardColors(
            containerColor = containerColor
        ),

        border = BorderStroke(2.dp, borderColor)
    ) {

        Text(
            text = text,

            modifier = Modifier.padding(18.dp),

            style = MaterialTheme.typography.bodyLarge,

            fontWeight =
                if (isSelected)
                    FontWeight.Bold
                else
                    FontWeight.Normal
        )
    }
}