AKSHARA-DEEPA TUTOR (Education)

AKSHARA-DEEPA TUTOR is a "Self-Learning & Performance Tracking System" designed to help SSLC students improve their academic performance through guided digital learning. By transforming the syllabus into an interactive learning platform, it allows students to track chapter progress, attempt quizzes, identify weak learning areas, and build consistent study habits through visual performance analysis.

Problem Statement
In many rural and self-study environments, students often depend only on textbooks without proper guidance or performance analysis systems. There is no effective system to "Track Chapter Mastery" or "Identify Weak Subjects," making it difficult for students to improve consistently and prepare effectively for examinations. AKSHARA-DEEPA TUTOR solves this by providing a digital learning platform with syllabus tracking, quiz evaluation, Strength Map visualization, and Gap Area identification.

Key Features
• Syllabus Tracker: Displays subject-wise and chapter-wise learning progress with dynamic indicators.
• Timer-Based Quiz System: Chapter-wise quizzes with countdown timers and automatic score calculation.
• Answer Review Screen: Displays correct answers, explanations, and quiz analysis after submission.
• Strength Map: Spider-Web (Radar) chart showing subject mastery percentages dynamically.
• Gap Area Detection: Identifies weaker learning areas based on quiz accuracy and performance analysis.
• Daily Reminder Notifications: WorkManager-based reminders encouraging students to complete at least one topic every day.
• Offline Learning Support: Stores quiz questions, progress data, and mastery information locally using Room Database.

Tech Stack & Architecture
• Language: Kotlin (100%)
• UI Framework: Jetpack Compose (Modern Material 3 UI)
• Database: Room Database (Offline storage for quizzes and progress tracking)
• Architecture: MVVM (Model-View-ViewModel)
• Navigation: Navigation3 for Jetpack Compose
• State Management: StateFlow + ViewModel
• Background Tasks: WorkManager (Daily study reminders)
• Chart Library: MPAndroidChart (Strength Map radar chart)
• Build System: Gradle with Kotlin DSL
• Version Control: Git & GitHub

```text
Folder Structure

app/src/main/java/com/example/aksharadeepatutor/
├── ui/              # Compose UI, Themes, and Screen Layouts
├── viewmodel/       # UI Logic and State Management
├── repository/      # Data handling and Repository layer
├── data/            # Room DB Entities and DAOs
├── model/           # Data Models
└── navigation/      # Navigation graphs and screen definitions

Installation Steps
• Clone the repository:git clone https://github.com/jeevitha2024-code/Android-Studio-Project.git
• Open Project:Launch Android Studio and select "Open" -> Navigate to the cloned folder.
• Sync Gradle:Allow Android Studio to download dependencies and sync the project.
• Build Project:Wait until Gradle build completes successfully.

Run Command
• Connect your Android device via USB or start a virtual emulator.
• Select the "app" configuration in the top toolbar.
• Press Shift + F10 or click the green "Run" button to install and launch the app on your device.

Future Improvements
• AI-Based Personalized Study Recommendations
• Cloud Synchronization using Firebase
• Multi-Language Support
• Adaptive Quiz Difficulty
• Gamification Features
• Teacher Dashboard and Analytics
• Voice-Assisted Learning Support
• Online Leaderboard System
