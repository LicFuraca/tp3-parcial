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
fun PantallaVerifyPhone(onNext: () -> Unit, onBack: () -> Unit) {
    var phoneNumber by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LendlyColors.Background.Screen)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // --- PARTE SUPERIOR: Flecha atrás y Títulos ---
        Column(modifier = Modifier.fillMaxWidth()) {
            TextButton(onClick = onBack, modifier = Modifier.align(Alignment.Start)) {
                Text("< Back", color = LendlyColors.Content.Primary)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Verify your phone number with a code",
                fontSize = 24.sp,
                color = LendlyColors.Content.Primary,
                lineHeight = 30.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "We will send you a One-Time-Password (OTP) to confirm your number.",
                fontSize = 14.sp,
                color = LendlyColors.Content.Secondary
            )
        }

        // --- PARTE CENTRAL: Inputs de Teléfono ---
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Your Phone Number",
                color = LendlyColors.Content.Primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = "+63",
                    onValueChange = {},
                    enabled = false,
                    modifier = Modifier.width(75.dp)
                )
                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    placeholder = { Text("9123456") },
                    modifier = Modifier.weight(1f) // Cambiado a la forma nativa de Compose
                )
            }
        }

        // --- PARTE INFERIOR: Botón Send Code ---
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
            Text(text = "Send Code", fontSize = 16.sp)
        }
    }
}