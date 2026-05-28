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
fun PantallaSignature(onNext: () -> Unit, onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LendlyColors.Background.Screen)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            TextButton(onClick = onBack, modifier = Modifier.align(Alignment.Start)) {
                Text("< Back", color = LendlyColors.Content.Primary)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Add your signature",
                fontSize = 24.sp,
                color = LendlyColors.Content.Primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Please sign inside the box below to complete your registration.",
                fontSize = 14.sp,
                color = LendlyColors.Content.Secondary
            )
        }

        // --- Marco para simular la firma ---
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(LendlyColors.Background.Neutral)
                .border(1.dp, LendlyColors.Interactive.Secondary, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "[ Firme aquí ]", color = LendlyColors.Content.Tertiary)
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