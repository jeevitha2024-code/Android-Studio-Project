package com.example.aksharadeepatutor.ui.navigation

import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.aksharadeepatutor.data.repository.QuizRepository
import com.example.aksharadeepatutor.ui.screens.*
import com.example.aksharadeepatutor.viewmodel.MainViewModel
import com.example.aksharadeepatutor.viewmodel.QuizViewModel

@Composable
fun NavGraph(
    repository: QuizRepository
) {
    val backStack = rememberNavBackStack(NavRoute.Home)

    NavDisplay(
        backStack = backStack,
        onBack = {
            if (backStack.size > 1) backStack.removeAt(backStack.lastIndex)
        },
        entryProvider = entryProvider {

            val mainViewModel: MainViewModel = viewModel {
                MainViewModel(repository)
            }

            // HOME
            entry<NavRoute.Home> {
                HomeScreen(
                    viewModel = mainViewModel,
                    onStartLearning = { backStack.add(NavRoute.SubjectSelection) },
                    onViewStrengthMap = { backStack.add(NavRoute.StrengthMap) }
                )
            }

            // STRENGTH MAP
            entry<NavRoute.StrengthMap> {
                StrengthMapScreen(
                    viewModel = mainViewModel,
                    onBack = {
                        if (backStack.size > 1)
                            backStack.removeAt(backStack.lastIndex)
                    }
                )
            }

            // SUBJECT
            entry<NavRoute.SubjectSelection> {
                SubjectSelectionScreen(
                    viewModel = mainViewModel,
                    onSubjectClick = { subject ->
                        backStack.add(
                            NavRoute.ChapterSelection(subject.id, subject.name)
                        )
                    },
                    onBack = {
                        if (backStack.size > 1)
                            backStack.removeAt(backStack.lastIndex)
                    }
                )
            }

            // CHAPTER
            entry<NavRoute.ChapterSelection> { route ->
                ChapterSelectionScreen(
                    viewModel = mainViewModel,
                    subjectId = route.subjectId,
                    subjectName = route.subjectName,
                    onChapterClick = { chapter ->
                        backStack.add(
                            NavRoute.Quiz(chapter.id, chapter.name)
                        )
                    },
                    onBack = {
                        if (backStack.size > 1)
                            backStack.removeAt(backStack.lastIndex)
                    }
                )
            }

            // QUIZ
            entry<NavRoute.Quiz> { route ->

                val quizViewModel: QuizViewModel = viewModel(
                    key = "quiz_${route.chapterId}"
                ) {
                    QuizViewModel(repository, route.chapterId)
                }

                // IMPORTANT: reset every time quiz screen is opened
                LaunchedEffect(route.chapterId) {
                    quizViewModel.resetQuiz()
                }

                QuizScreen(
                    viewModel = quizViewModel,
                    chapterName = route.chapterName,
                    onExit = {
                        if (backStack.size > 1)
                            backStack.removeAt(backStack.lastIndex)
                    },
                    onFinished = { score, total ->
                        if (backStack.size > 1)
                            backStack.removeAt(backStack.lastIndex)

                        backStack.add(
                            NavRoute.Results(score, total, route.chapterId)
                        )
                    }
                )
            }

            // RESULTS
            entry<NavRoute.Results> { route ->

                ResultsScreen(
                    score = route.score,
                    total = route.total,

                    onRetry = {
                        // remove Results screen
                        if (backStack.isNotEmpty())
                            backStack.removeAt(backStack.lastIndex)

                        // remove Quiz screen
                        if (backStack.isNotEmpty())
                            backStack.removeAt(backStack.lastIndex)

                        // reopen Quiz (fresh start)
                        backStack.add(
                            NavRoute.Quiz(route.chapterId, "Quiz")
                        )
                    },

                    onReview = {
                        backStack.add(
                            NavRoute.Review(route.chapterId)
                        )
                    },

                    onHome = {
                        while (backStack.size > 1) {
                            backStack.removeAt(backStack.lastIndex)
                        }
                    }
                )
            }

            // REVIEW
            entry<NavRoute.Review> { route ->

                val quizViewModel: QuizViewModel = viewModel(
                    key = "quiz_${route.chapterId}"
                ) {
                    QuizViewModel(repository, route.chapterId)
                }

                val state by quizViewModel.uiState.collectAsState()

                ReviewScreen(
                    questions = state.questions,
                    userAnswers = state.userAnswers,
                    onBack = {
                        if (backStack.size > 1)
                            backStack.removeAt(backStack.lastIndex)
                    }
                )
            }
        }
    )
}