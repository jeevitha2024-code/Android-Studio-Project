package com.example.aksharadeepatutor.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aksharadeepatutor.data.repository.QuizRepository
import com.example.aksharadeepatutor.model.Question
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class QuizState(
    val questions: List<Question> = emptyList(),
    val currentQuestionIndex: Int = 0,
    val selectedOptionIndex: Int? = null,
    val isAnswerConfirmed: Boolean = false,
    val score: Int = 0,
    val timeLeft: Int = 30,
    val isQuizFinished: Boolean = false,
    val userAnswers: List<Int?> = emptyList()
)

class QuizViewModel(
    private val repository: QuizRepository,
    private val chapterId: Int
) : ViewModel() {

    private val _uiState = MutableStateFlow(QuizState())
    val uiState: StateFlow<QuizState> = _uiState.asStateFlow()

    private var timerJob: Job? = null

    init {
        loadQuestions()
    }

    // ---------------- LOAD QUESTIONS ----------------
    private fun loadQuestions() {
        viewModelScope.launch {
            repository.getQuestions(chapterId).collectLatest { questions ->

                val selectedQuestions = questions.shuffled().take(5)

                _uiState.value = QuizState(
                    questions = selectedQuestions,
                    userAnswers = List(selectedQuestions.size) { null }
                )

                startTimer()
            }
        }
    }

    // ---------------- TIMER ----------------
    private fun startTimer() {
        timerJob?.cancel()

        _uiState.update { it.copy(timeLeft = 30) }

        timerJob = viewModelScope.launch {
            while (true) {

                val state = _uiState.value

                if (state.isQuizFinished) break

                if (state.timeLeft <= 0) {
                    if (!state.isAnswerConfirmed) {
                        onOptionSelected(-1)
                    }
                    break
                }

                delay(1000)

                _uiState.update {
                    it.copy(timeLeft = it.timeLeft - 1)
                }
            }
        }
    }

    // ---------------- OPTION SELECT ----------------
    fun onOptionSelected(optionIndex: Int) {
        if (_uiState.value.isAnswerConfirmed) return

        timerJob?.cancel()

        val state = _uiState.value
        val question = state.questions[state.currentQuestionIndex]

        val isCorrect = optionIndex == question.correctAnswerIndex

        val updatedAnswers = state.userAnswers.toMutableList()
        updatedAnswers[state.currentQuestionIndex] =
            if (optionIndex == -1) null else optionIndex

        _uiState.update {
            it.copy(
                selectedOptionIndex = optionIndex,
                isAnswerConfirmed = true,
                score = if (isCorrect) it.score + 1 else it.score,
                userAnswers = updatedAnswers
            )
        }
    }

    // ---------------- NEXT QUESTION ----------------
    fun goToNextQuestion() {
        val nextIndex = _uiState.value.currentQuestionIndex + 1

        if (nextIndex < _uiState.value.questions.size) {
            _uiState.update {
                it.copy(
                    currentQuestionIndex = nextIndex,
                    selectedOptionIndex = null,
                    isAnswerConfirmed = false,
                    timeLeft = 30
                )
            }
            startTimer()
        } else {
            finishQuiz()
        }
    }

    // ---------------- SUBMIT QUIZ ----------------
    fun submitQuiz() {
        finishQuiz()
    }

    // ---------------- FINISH QUIZ (FIXED) ----------------
    private fun finishQuiz() {

        timerJob?.cancel()

        _uiState.update { it.copy(isQuizFinished = true) }

        val state = _uiState.value

        val correct = state.score
        val total = state.questions.size

        val accuracy = if (total > 0) {
            (correct * 100) / total
        } else 0

        viewModelScope.launch {
            repository.saveProgress(
                chapterId = chapterId,
                correct = correct,
                totalQuestions = total,
                accuracy = accuracy
            )
        }
    }

    // ---------------- RESET QUIZ ----------------
    fun resetQuiz() {
        timerJob?.cancel()

        val questions = _uiState.value.questions

        _uiState.value = QuizState(
            questions = questions,
            userAnswers = List(questions.size) { null },
            currentQuestionIndex = 0,
            score = 0,
            timeLeft = 30,
            isQuizFinished = false,
            selectedOptionIndex = null,
            isAnswerConfirmed = false
        )

        startTimer()
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}