package com.example.aksharadeepatutor.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.EmojiEvents
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ResultsScreen(
    score: Int,
    total: Int,
    onRetry: () -> Unit,
    onReview: () -> Unit,
    onHome: () -> Unit
) {
    val percentage = (score.toFloat() / total * 100).toInt()
    val (message, subMessage) = getMotivationalContent(percentage)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Rounded.EmojiEvents,
            contentDescription = null,
            modifier = Modifier.size(100.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            text = "Quiz Completed!",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = "$score / $total",
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Black
        )
        
        Text(
            text = "$percentage%",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Text(
            text = message,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        
        Text(
            text = subMessage,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        
        Spacer(modifier = Modifier.height(48.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ResultActionCard(
                title = "Retry",
                icon = Icons.Rounded.Refresh,
                modifier = Modifier.weight(1f),
                onClick = onRetry
            )
            ResultActionCard(
                title = "Review",
                icon = Icons.Rounded.Visibility,
                modifier = Modifier.weight(1f),
                onClick = onReview
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Button(
            onClick = onHome,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp)
        ) {
            Icon(Icons.Rounded.Home, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Back to Home")
        }
    }
}

@Composable
fun ResultActionCard(
    title: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    OutlinedCard(
        onClick = onClick,
        modifier = modifier.height(100.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(icon, contentDescription = null)
            Spacer(modifier = Modifier.height(8.dp))
            Text(title, style = MaterialTheme.typography.labelLarge)
        }
    }
}

private fun getMotivationalContent(percentage: Int): Pair<String, String> {
    return when {
        percentage >= 90 -> "Excellent Work!" to "You've mastered this chapter. Keep up the great momentum!"
        percentage >= 70 -> "Great Job!" to "You have a solid understanding. A little more practice and you'll be perfect!"
        percentage >= 40 -> "Good Effort!" to "You're getting there. Review the tricky parts and try again."
        else -> "Keep Learning!" to "Don't give up! Every mistake is a learning opportunity. Let's try once more."
    }
}
