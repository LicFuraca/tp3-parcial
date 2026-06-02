package com.example.parcial_grupo_4.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.parcial_grupo_4.ui.theme.*

@Composable
fun LendlyBottomAction(
    text: String,
    onClick: () -> Unit,
    showDivider: Boolean = true
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        if (showDivider) {
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = LendlyColors.Interactive.Secondary.copy(alpha = 0.3f)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = LendlySpacing.Spacing4, vertical = 16.dp)
        ) {
            PrimaryButton(text = text, onClick = onClick)
        }
    }
}