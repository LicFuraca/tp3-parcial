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
fun PantallaDone(onGetStarted: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LendlyColors.Background.Screen)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "🎉", fontSize = 64.sp, modifier = Modifier.padding(bottom = 16.dp))
            Text(text = "All done!", fontSize = 28.sp, color = LendlyColors.Content.Primary, modifier = Modifier.padding(bottom = 8.dp))
            Text(text = "Your account is ready to use. Thank you for completing the setup.", fontSize = 14.sp, color = LendlyColors.Content.Secondary)
        }

        Button(
            onClick = onGetStarted,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = LendlyColors.Interactive.Accent,
                contentColor = LendlyColors.Content.Primary
            )
        ) {
            Text(text = "Get Started", fontSize = 16.sp)
        }
    }
}