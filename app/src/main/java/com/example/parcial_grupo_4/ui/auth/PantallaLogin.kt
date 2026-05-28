package com.example.parcial_grupo_4.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
// Importamos el theme oficial del grupo
import com.example.parcial_grupo_4.ui.theme.*

@Composable
fun PantallaLogin(onNext: () -> Unit) {
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            // Usamos el fondo neutral oficial del grupo
            .background(LendlyColors.Background.Neutral)
            .padding(horizontal = LendlySpacing.Spacing4, vertical = LendlySpacing.Spacing5), // Usamos los Spacing oficiales
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // --- PARTE SUPERIOR: Logo Lendly y bienvenida ---
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = LendlySpacing.Spacing3)
        ) {
            Text(
                text = "Lendly",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = LendlyColors.Content.Primary // Color oficial
            )
            Spacer(modifier = Modifier.height(LendlySpacing.Spacing3))
            Text(
                text = "John Doe",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = LendlyColors.Content.Primary
            )
            Text(
                text = "+63 9123456790",
                fontSize = 14.sp,
                color = LendlyColors.Content.Secondary,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        // --- PARTE CENTRAL: Input de Contraseña ---
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = LendlySpacing.Spacing1)
        ) {
            Text(
                text = "Password",
                color = LendlyColors.Content.Primary,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = LendlySpacing.Spacing1)
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("••••••••", color = LendlyColors.Content.Tertiary) },
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = LendlyColors.Interactive.Primary,
                    unfocusedBorderColor = LendlyColors.Interactive.Secondary,
                    focusedContainerColor = LendlyColors.Background.Screen,
                    unfocusedContainerColor = LendlyColors.Background.Screen
                )
            )

            Spacer(modifier = Modifier.height(LendlySpacing.Spacing2))

            TextButton(
                onClick = { /* Acción para recuperar clave */ },
                modifier = Modifier.align(Alignment.Start)
            ) {
                Text(
                    text = "Forgot your password?",
                    color = LendlyColors.Content.Link,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        // --- PARTE INFERIOR: Botón Log In ---
        Button(
            onClick = onNext,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(100.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = LendlyColors.Interactive.Accent, // Verde brillante
                contentColor = LendlyColors.Content.Primary       // Letras oscuras
            )
        ) {
            Text(
                text = "Log In",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}