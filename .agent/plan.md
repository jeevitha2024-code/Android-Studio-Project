# Project Plan

Create a complete Android educational quiz app named "Akshara Deepa Tutor" using Kotlin, Jetpack Compose, Room Database, MVVM architecture, and Material 3 design. The app should be modern, smooth, colorful, responsive, and beginner friendly.

Screens:
- Home: Title, Progress, Start, Strength Map, Recent Activity.
- Subjects: Math, Science, Social Science.
- Chapters: 5 specific chapters per subject.
- Quiz: 10 MCQ per chapter, Timer, Progress, Transitions.
- Results: Score, Motivational message, Buttons.
- Review: Highlight answers.
- Strength Map: Progress bars, Tips.

Database: Room with seed data for all questions.
Architecture: MVVM + Repository + StateFlow.
UI: Material 3, Edge-to-Edge, Adaptive Icon.

## Project Brief

# Akshara Deepa Tutor

Akshara Deepa Tutor is a modern, student-centric Android educational application designed to facilitate learning through interactive quizzes. The app provides a structured curriculum across Mathematics, Science, and Social Science, offering data-driven insights into student performance through a "Strength Map" and detailed progress tracking.

### Features
1. **Interactive Quiz Engine**: A robust assessment system featuring timed multiple-choice questions (MCQs), real-time progress indicators, and immediate feedback with correct answer highlighting.
2. **Adaptive Curriculum Catalog**: A subject and chapter navigation system (Mathematics, Science, Social Science) that organizes educational content into manageable learning modules.
3. **Performance Analytics (Strength Map)**: A data-driven dashboard that visualizes subject-wise progress, identifies weak areas, and provides personalized learning tips.
4. **Comprehensive Review & Results**: Post-quiz analysis featuring motivational messaging, score calculation, and a detailed review screen comparing selected answers against correct ones.

### High-Level Tech Stack
* **Language**: Kotlin
* **UI Framework**: Jetpack Compose with Material 3 (supporting Edge-to-Edge and Adaptive layouts)
* **Navigation**: Jetpack Navigation 3 (state-driven architecture)
* **Adaptive Strategy**: Compose Material Adaptive Library (supporting various form factors)
* **Concurrency & State**: Kotlin Coroutines and StateFlow
* **Persistence**: Room Database (for quiz content, seed data, and student progress tracking)
* **Architecture**: MVVM (Model-View-ViewModel)

## Implementation Steps

### Task_1_Data_Layer: Setup Room database with entities for Subjects, Chapters, Questions, and UserProgress. Pre-populate the database with content for 3 subjects (Math, Science, Social Science), 5 chapters each, and 10 MCQs per chapter.
- **Status:** COMPLETED
- **Updates:** Successfully implemented the Room database with entities for Subjects, Chapters, Questions, and UserProgress. Seeded 150 real educational MCQ questions across 3 subjects and 15 chapters. Implemented Repository pattern and verified data integrity with tests.
- **Acceptance Criteria:**
  - Room database and Repository implemented
  - All 150 questions seeded in DB
  - Database operations successfully fetch quiz content

### Task_2_UI_Foundation: Configure Navigation 3 and Material 3 theme. Implement the Home Screen, Subject Selection Screen, and Chapter Selection Screen using adaptive layouts and M3 components.
- **Status:** COMPLETED
- **Updates:** Implemented Navigation 3 and Material 3 theme with a vibrant color scheme. Created Home, Subject, and Chapter screens with adaptive layouts and progress tracking. Successfully integrated the UI with the previously built Room repository via MainViewModel. App supports light/dark modes and edge-to-edge display.
- **Acceptance Criteria:**
  - Navigation 3 handles transitions between selection screens
  - Home screen displays progress and subject shortcuts
  - Material 3 theme applied with vibrant colors
- **Duration:** N/A

### Task_3_Quiz_Engine_Analytics: Implement the Quiz screen with timer and MCQ logic. Create the Results screen with motivational messages, the Review screen for answer feedback, and the Strength Map dashboard.
- **Status:** IN_PROGRESS
- **Acceptance Criteria:**
  - Quiz engine correctly calculates scores and tracks time
  - Results and Review screens display accurate post-quiz data
  - Strength Map visualizes performance across subjects
- **StartTime:** 2026-05-11 22:05:23 IST

### Task_4_Polishing_Verification: Implement Edge-to-Edge display, create an adaptive app icon matching the tutor theme, and perform a final verification of the application stability and requirements.
- **Status:** PENDING
- **Acceptance Criteria:**
  - Full Edge-to-Edge display implemented
  - Adaptive app icon is functional
  - App builds and runs without crashes
  - All existing tests pass

