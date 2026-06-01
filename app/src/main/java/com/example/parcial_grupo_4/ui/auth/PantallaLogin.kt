package com.example.parcial_grupo_4.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcial_grupo_4.R
import com.example.parcial_grupo_4.ui.theme.*
import com.example.parcial_grupo_4.ui.common.LendlyBottomAction

@Composable
fun PantallaLogin(onNext: () -> Unit) {
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LendlyColors.Background.Screen)
            .padding(vertical = LendlySpacing.Spacing4)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = LendlySpacing.Spacing4),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo_layers),
                contentDescription = "Lendly Logo",
                modifier = Modifier
                    .size(140.dp)
                    .padding(vertical = LendlySpacing.Spacing1)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = LendlySpacing.Spacing2),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(LendlyColors.Background.Neutral),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "JD",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = LendlyColors.Content.Primary
                    )
                }

                Spacer(modifier = Modifier.width(LendlySpacing.Spacing2))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "John Doe",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = LendlyColors.Content.Primary
                    )
                    Text(
                        text = "+63-923456790",
                        fontSize = 14.sp,
                        color = LendlyColors.Content.Secondary
                    )
                }

                Text(
                    text = "Change",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = LendlyColors.Content.Link,
                    textDecoration = TextDecoration.Underline
                )
            }

            Spacer(modifier = Modifier.height(LendlySpacing.Spacing2))

            Column(modifier = Modifier.fillMaxWidth()) {
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
                    placeholder = { Text("123123123", color = LendlyColors.Content.Tertiary) },
                    visualTransformation = PasswordVisualTransformation(),
                    shape = RoundedCornerShape(12.dp),
                    trailingIcon = {
                        IconButton(onClick = { }) {
                            Text("👁", fontSize = 16.sp)
                        }
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = LendlyColors.Interactive.Secondary,
                        unfocusedBorderColor = LendlyColors.Interactive.Secondary,
                        focusedContainerColor = LendlyColors.Background.Screen,
                        unfocusedContainerColor = LendlyColors.Background.Screen
                    )
                )

                Spacer(modifier = Modifier.height(LendlySpacing.Spacing2))

                Text(
                    text = "Forgot your password?",
                    color = LendlyColors.Content.Link,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }

        LendlyBottomAction(
            text = "Log In",
            onClick = onNext,
            showDivider = true
        )
    }
}