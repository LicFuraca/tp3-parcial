package com.example.parcial_grupo_4.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcial_grupo_4.ui.theme.*

@Composable
fun PantallaVerifiedPage(onNext: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LendlyColors.Background.Screen)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // --- PARTE SUPERIOR: Espacio vacío para empujar el contenido al centro ---
        Spacer(modifier = Modifier.height(20.dp))

        // --- PARTE CENTRAL: Ícono de éxito y textos de confirmación ---
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        ) {
            // Un círculo o texto grande que simule el tilde de verificado
            Text(
                text = "✓",
                fontSize = 72.sp,
                color = LendlyColors.Interactive.Accent, // El verde brillante del Figma
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Text(
                text = "You are verified!",
                fontSize = 26.sp,
                color = LendlyColors.Content.Primary,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Text(
                text = "Your identity has been successfully confirmed. Let's continue setting up your account.",
                fontSize = 15.sp,
                color = LendlyColors.Content.Secondary,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }

        // --- PARTE INFERIOR: Botón Next ---
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