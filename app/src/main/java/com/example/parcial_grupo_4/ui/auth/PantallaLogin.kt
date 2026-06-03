package com.example.parcial_grupo_4.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.parcial_grupo_4.R
import com.example.parcial_grupo_4.ui.theme.*
import com.example.parcial_grupo_4.ui.common.LendlyBottomAction
import androidx.compose.ui.res.stringResource

@Composable
fun PantallaLogin(
    onNext: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel() // Inyectamos el ViewModel
) {
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var localError by remember { mutableStateOf<String?>(null) }
    val loginState by viewModel.loginState.collectAsStateWithLifecycle()

    // Manejo del estado para navegar
    LaunchedEffect(loginState) {
        if (loginState is LoginState.Success) {
            onNext()
        }
    }

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
            // ... (Tu diseño se mantiene igual)
            Image(
                painter = painterResource(id = R.drawable.ic_logo_layers),
                contentDescription = "Lendly Logo",
                modifier = Modifier
                    .size(140.dp)
                    .padding(vertical = LendlySpacing.Spacing1)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // ... (Resto de tu diseño de perfil)

            // Sección de Teléfono
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Phone Number",
                    color = LendlyColors.Content.Primary,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = LendlySpacing.Spacing1)
                )

                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("+63-923456790", color = LendlyColors.Content.Tertiary) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = LendlyColors.Interactive.Secondary,
                        unfocusedBorderColor = LendlyColors.Interactive.Secondary,
                        focusedContainerColor = LendlyColors.Background.Screen,
                        unfocusedContainerColor = LendlyColors.Background.Screen
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Sección de Password
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
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = LendlyColors.Interactive.Secondary,
                        unfocusedBorderColor = LendlyColors.Interactive.Secondary,
                        focusedContainerColor = LendlyColors.Background.Screen,
                        unfocusedContainerColor = LendlyColors.Background.Screen
                    )
                )

                if (loginState is LoginState.Error) {
                    Text(
                        text = stringResource(id = (loginState as LoginState.Error).messageRes),
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                localError?.let { error ->
                    Text(
                        text = error,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }

        // Aquí conectamos el botón de login
        LendlyBottomAction(
            text = if (loginState is LoginState.Loading) "Cargando..." else "Log In",
            onClick = {
                if (phone.isBlank() || password.isBlank()) {
                    localError = "Completá teléfono y contraseña"
                } else {
                    localError = null
                    viewModel.login(phone, password)
                }
            },
            showDivider = true
        )
    }
}