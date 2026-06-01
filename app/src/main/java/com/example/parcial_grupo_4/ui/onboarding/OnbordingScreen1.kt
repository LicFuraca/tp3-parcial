package com.example.parcial_grupo_4.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OnboardingScreen1(
    onNext: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF163300)) // Usá tu color de fondo real; podés usar colorResource si querés
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // Icono billete + tarjetas
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Center
            ) {
                // Icono billete
                Icon(
                    painter = painterResource(id = R.drawable.ic_money),
                    contentDescription = "Money icon",
                    tint = Color(0xFF7BF179),
                    modifier = Modifier
                        .size(50.dp)
                        .background(colorResource(id = R.color.dark_green), shape = CircleShape)
                        .padding(8.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    LoanCard()
                    Spacer(modifier = Modifier.height(4.dp))
                    LoanCard()
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text("👏", fontSize = 30.sp, modifier = Modifier.align(Alignment.End))
            Spacer(modifier = Modifier.height(4.dp))

            Image(
                painter = painterResource(id = R.drawable.avatar_onboarding_1),
                contentDescription = "Avatar",
                modifier = Modifier
                    .height(180.dp)
                    .clip(RoundedCornerShape(24.dp))
            )

            Spacer(modifier = Modifier.height(12.dp))
            Text("😁", fontSize = 30.sp, modifier = Modifier.align(Alignment.Start))
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.title_onboarding_1),
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFF7BF179),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Text(
                text = stringResource(R.string.subtitle_onboarding_1),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Dot(active = true)
                Dot(active = false)
                Dot(active = false)
            }

            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = onNext,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7BF179)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
            ) {
                Text(text = stringResource(R.string.onboarding_btn_get_started), color = Color.Black)
            }

            Spacer(modifier = Modifier.height(12.dp))
            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(64.dp)
                    .height(4.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.8f))
            )
        }
    }
}

// ... LoanCard y Dot iguales a lo anterior