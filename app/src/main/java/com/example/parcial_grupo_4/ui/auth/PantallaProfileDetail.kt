package com.example.parcial_grupo_4.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcial_grupo_4.R
import com.example.parcial_grupo_4.ui.theme.*
import com.example.parcial_grupo_4.ui.common.PrimaryButton
import com.example.parcial_grupo_4.ui.common.LendlyBottomAction

@Composable
fun PantallaProfileDetail(onNext: () -> Unit, onBack: () -> Unit) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var birthDay by remember { mutableStateOf("") }
    var birthMonth by remember { mutableStateOf("") }
    var birthYear by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var postalCode by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LendlyColors.Background.Screen)
            .padding(vertical = LendlySpacing.Spacing4),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
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
                    .verticalScroll(scrollState)
                    .padding(horizontal = LendlySpacing.Spacing4)
            ) {
                Text(
                    text = "Enter your personal\ndetails",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 36.sp,
                    color = LendlyColors.Content.Primary,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                Text(text = "Full legal first and middle name(s)", color = LendlyColors.Content.Primary, fontSize = 14.sp, modifier = Modifier.padding(bottom = 8.dp))
                OutlinedTextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    placeholder = { Text("John D.", color = LendlyColors.Content.Tertiary) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = LendlyColors.Interactive.Primary,
                        unfocusedBorderColor = LendlyColors.Interactive.Secondary,
                        focusedContainerColor = LendlyColors.Background.Screen,
                        unfocusedContainerColor = LendlyColors.Background.Screen
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Full legal last name", color = LendlyColors.Content.Primary, fontSize = 14.sp, modifier = Modifier.padding(bottom = 8.dp))
                OutlinedTextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    placeholder = { Text("Doe", color = LendlyColors.Content.Tertiary) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = LendlyColors.Interactive.Primary,
                        unfocusedBorderColor = LendlyColors.Interactive.Secondary,
                        focusedContainerColor = LendlyColors.Background.Screen,
                        unfocusedContainerColor = LendlyColors.Background.Screen
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Date of birth", color = LendlyColors.Content.Primary, fontSize = 14.sp, modifier = Modifier.padding(bottom = 8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "Day", color = LendlyColors.Content.Secondary, fontSize = 14.sp, modifier = Modifier.padding(bottom = 4.dp))
                        OutlinedTextField(
                            value = birthDay,
                            onValueChange = { if (it.length <= 2) birthDay = it },
                            placeholder = { Text("08", color = LendlyColors.Content.Tertiary) },
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = LendlyColors.Interactive.Primary,
                                unfocusedBorderColor = LendlyColors.Interactive.Secondary,
                                focusedContainerColor = LendlyColors.Background.Screen,
                                unfocusedContainerColor = LendlyColors.Background.Screen
                            )
                        )
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "Month", color = LendlyColors.Content.Secondary, fontSize = 14.sp, modifier = Modifier.padding(bottom = 4.dp))
                        OutlinedTextField(
                            value = birthMonth,
                            onValueChange = { if (it.length <= 2) birthMonth = it },
                            placeholder = { Text("12", color = LendlyColors.Content.Tertiary) },
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = LendlyColors.Interactive.Primary,
                                unfocusedBorderColor = LendlyColors.Interactive.Secondary,
                                focusedContainerColor = LendlyColors.Background.Screen,
                                unfocusedContainerColor = LendlyColors.Background.Screen
                            )
                        )
                    }
                    Column(modifier = Modifier.weight(1.5f)) {
                        Text(text = "Year", color = LendlyColors.Content.Secondary, fontSize = 14.sp, modifier = Modifier.padding(bottom = 4.dp))
                        OutlinedTextField(
                            value = birthYear,
                            onValueChange = { if (it.length <= 4) birthYear = it },
                            placeholder = { Text("1997", color = LendlyColors.Content.Tertiary) },
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

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Address", color = LendlyColors.Content.Primary, fontSize = 14.sp, modifier = Modifier.padding(bottom = 8.dp))
                OutlinedTextField(
                    value = address,
                    onValueChange = { address = it },
                    placeholder = { Text("Somewhere IN BLOCK 12", color = LendlyColors.Content.Tertiary) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = LendlyColors.Interactive.Primary,
                        unfocusedBorderColor = LendlyColors.Interactive.Secondary,
                        focusedContainerColor = LendlyColors.Background.Screen,
                        unfocusedContainerColor = LendlyColors.Background.Screen
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "City", color = LendlyColors.Content.Primary, fontSize = 14.sp, modifier = Modifier.padding(bottom = 8.dp))
                OutlinedTextField(
                    value = city,
                    onValueChange = { city = it },
                    placeholder = { Text("Davao City", color = LendlyColors.Content.Tertiary) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = LendlyColors.Interactive.Primary,
                        unfocusedBorderColor = LendlyColors.Interactive.Secondary,
                        focusedContainerColor = LendlyColors.Background.Screen,
                        unfocusedContainerColor = LendlyColors.Background.Screen
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Postal Code", color = LendlyColors.Content.Primary, fontSize = 14.sp, modifier = Modifier.padding(bottom = 8.dp))
                OutlinedTextField(
                    value = postalCode,
                    onValueChange = { postalCode = it },
                    placeholder = { Text("8000", color = LendlyColors.Content.Tertiary) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = LendlyColors.Interactive.Primary,
                        unfocusedBorderColor = LendlyColors.Interactive.Secondary,
                        focusedContainerColor = LendlyColors.Background.Screen,
                        unfocusedContainerColor = LendlyColors.Background.Screen
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Phone Number", color = LendlyColors.Content.Primary, fontSize = 14.sp, modifier = Modifier.padding(bottom = 8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = "+65",
                        onValueChange = {},
                        enabled = false,
                        modifier = Modifier.width(75.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            disabledBorderColor = LendlyColors.Interactive.Secondary,
                            disabledContainerColor = LendlyColors.Background.Screen,
                            disabledTextColor = LendlyColors.Content.Primary
                        )
                    )
                    OutlinedTextField(
                        value = phoneNumber,
                        onValueChange = { phoneNumber = it },
                        placeholder = { Text("991251255", color = LendlyColors.Content.Tertiary) },
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
                Spacer(modifier = Modifier.height(100.dp))
            }
        }

        LendlyBottomAction(text = "Next", onClick = onNext)
    }
}