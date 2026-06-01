package com.example.parcial_grupo_4.ui.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcial_grupo_4.ui.theme.LendlyColors

@Composable
fun CreditScoreCard(
    score: Int,
    maxScore: Int = 1000,
    status: String = "Good",
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Credit Score",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Box(contentAlignment = Alignment.Center) {
                // Simple Gauge Representation
                Canvas(modifier = Modifier.size(150.dp)) {
                    drawArc(
                        color = LendlyColors.Background.Neutral,
                        startAngle = 180f,
                        sweepAngle = 180f,
                        useCenter = false,
                        style = Stroke(width = 12.dp.toPx(), cap = StrokeCap.Round)
                    )
                    drawArc(
                        color = LendlyColors.Interactive.Accent,
                        startAngle = 180f,
                        sweepAngle = (score.toFloat() / maxScore.toFloat()) * 180f,
                        useCenter = false,
                        style = Stroke(width = 12.dp.toPx(), cap = StrokeCap.Round)
                    )
                }
                
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = score.toString(),
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.ExtraBold,
                        color = LendlyColors.Content.Primary
                    )
                    Text(
                        text = "Your Score is $status",
                        style = MaterialTheme.typography.labelMedium,
                        color = LendlyColors.Sentiment.Positive // Sentiment Positive
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            OutlinedButton(
                onClick = onClick,
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Find out your credit score", fontSize = 12.sp)
            }
        }
    }
}
