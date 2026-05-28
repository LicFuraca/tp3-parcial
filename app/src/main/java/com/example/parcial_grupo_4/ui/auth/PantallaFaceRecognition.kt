package com.example.parcial_grupo_4.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcial_grupo_4.ui.theme.*

@Composable
fun PantallaFaceRecognition(onNext: () -> Unit, onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LendlyColors.Background.Neutral)
            .padding(LendlySpacing.Spacing4),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // --- PARTE SUPERIOR: Volver y Textos ---
        Column(modifier = Modifier.fillMaxWidth()) {
            TextButton(onClick = onBack, modifier = Modifier.align(Alignment.Start)) {
                Text("< Back", color = LendlyColors.Content.Primary)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Put your face in the frame",
                fontSize = 24.sp,
                color = LendlyColors.Content.Primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Follow these instruction, and let us get you onboarded.",
                fontSize = 14.sp,
                color = LendlyColors.Content.Primary
            )
        }

        Box(
            modifier = Modifier
                .size(250.dp) // Un cuadrado intermedio
                .clip(RoundedCornerShape(12.dp))
                .background(LendlyColors.Background.Neutral)
                .border(2.dp, LendlyColors.Interactive.Accent, RoundedCornerShape(12.dp)), // Borde verde brillante
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "[ Visor de Cámara ]",
                color = LendlyColors.Content.Secondary,
                fontSize = 14.sp
            )
        }


        Button(
            onClick = onNext,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = LendlyColors.Interactive.Accent,
                contentColor = LendlyColors.Content.Primary
            )
        ) {
            Text(text = "Next", fontSize = 16.sp)
        }
    }
}