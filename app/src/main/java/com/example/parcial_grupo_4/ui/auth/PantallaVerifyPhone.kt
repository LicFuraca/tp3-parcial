package com.example.parcial_grupo_4.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcial_grupo_4.R
import com.example.parcial_grupo_4.ui.theme.*
import com.example.parcial_grupo_4.ui.common.LendlyBottomAction

@Composable
fun PantallaVerifyPhone(onNext: (String) -> Unit, onBack: () -> Unit) {
    var phoneNumber by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LendlyColors.Background.Screen)
            .padding(vertical = LendlySpacing.Spacing4),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBack,
                    modifier = Modifier.minimumInteractiveComponentSize()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = "Back",
                        tint = LendlyColors.Content.Primary
                    )
                }
                IconButton(
                    onClick = { },
                    modifier = Modifier.minimumInteractiveComponentSize()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_info),
                        contentDescription = "Information",
                        tint = LendlyColors.Content.Primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LendlySpacing.Spacing4)
            ) {
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

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "Your Phone Number",
                    color = LendlyColors.Content.Primary,
                    modifier = Modifier.padding(bottom = 8.dp),
                    fontSize = 14.sp
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = "+65",
                        onValueChange = {},
                        enabled = false, // Sigue sin ser editable por el usuario
                        modifier = Modifier.width(75.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            // Forzamos a que use exactamente los mismos colores de un campo común
                            disabledBorderColor = LendlyColors.Interactive.Secondary,
                            disabledContainerColor = LendlyColors.Background.Screen,
                            disabledTextColor = LendlyColors.Content.Primary
                        )
                    )
                    OutlinedTextField(
                        value = phoneNumber,
                        onValueChange = { phoneNumber = it },
                        placeholder = { Text("91251255", color = LendlyColors.Content.Tertiary) },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = LendlyColors.Interactive.Primary,
                            unfocusedBorderColor = LendlyColors.Interactive.Secondary,
                            focusedContainerColor = LendlyColors.Background.Screen,
                            unfocusedContainerColor = LendlyColors.Background.Screen
                        )
                    )
                }
            }
        }

        LendlyBottomAction(text = "Next", onClick = { onNext("+65$phoneNumber") })
    }
}