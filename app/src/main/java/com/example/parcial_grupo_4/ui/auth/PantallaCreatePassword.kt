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
fun PantallaCreatePassword(onNext: () -> Unit, onBack: () -> Unit) {
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LendlyColors.Background.Screen)
            .padding(LendlySpacing.Spacing4), // Aprovechamos el espaciado oficial (24.dp)
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            TextButton(onClick = onBack, modifier = Modifier.align(Alignment.Start)) {
                Text("< Back", color = LendlyColors.Content.Primary)
            }

            Spacer(modifier = Modifier.height(LendlySpacing.Spacing3))

            Text(
                text = "Create a password",
                fontSize = 24.sp,
                color = LendlyColors.Content.Primary,
                modifier = Modifier.padding(bottom = LendlySpacing.Spacing4)
            )

            Text(text = "Password", color = LendlyColors.Content.Primary, modifier = Modifier.padding(bottom = LendlySpacing.Spacing1))
            OutlinedTextField(
                value = newPassword,
                onValueChange = { newPassword = it },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(LendlySpacing.Spacing3))

            Text(text = "Confirm Password", color = LendlyColors.Content.Primary, modifier = Modifier.padding(bottom = LendlySpacing.Spacing1))
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                modifier = Modifier.fillMaxWidth()
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