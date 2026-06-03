package com.example.parcial_grupo_4.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.parcial_grupo_4.ui.theme.LendlyColors

private val ActiveDotColor = LendlyColors.Interactive.Accent
private val InactiveDotColor = LendlyColors.Border.Subtle

@Composable
fun NotificationItem(
    title: String,
    body: String,
    date: String,
    active: Boolean,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
    ) {
        Spacer(
            modifier = Modifier
                .size(8.dp)
                .background(
                    color = if (active) ActiveDotColor else InactiveDotColor,
                    shape = CircleShape,
                ),
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f),
        ) {
            Row {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelLarge,
                    color = Color(0xFF454745),
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f),
                )

                Text(
                    text = date,
                    style = MaterialTheme.typography.labelMedium,
                    color = Color(0xFF6A6C6A),
                )
            }

            Text(
                text = body,
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF6A6C6A),
            )
        }
    }
}