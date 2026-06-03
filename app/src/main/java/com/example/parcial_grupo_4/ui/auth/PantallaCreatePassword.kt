package com.example.parcial_grupo_4.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcial_grupo_4.R
import com.example.parcial_grupo_4.ui.theme.*
import com.example.parcial_grupo_4.ui.common.LendlyBottomAction

@Composable
fun PantallaCreatePassword(
    onSuccess: () -> Unit,
    onBack: () -> Unit,
    viewModel: RegisterViewModel
) {
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var localError by remember { mutableStateOf<String?>(null) }
    val registerState by viewModel.registerState.observeAsState(RegisterState.Idle)

    // Cuando el registro termina OK (token ya guardado), avanzamos al Done
    LaunchedEffect(registerState) {
        if (registerState is RegisterState.Success) {
            onSuccess()
        }
    }

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
                IconButton(onClick = onBack) {
                    Icon(painter = painterResource(id = R.drawable.ic_arrow_back), contentDescription = "Back", tint = LendlyColors.Content.Primary)
                }
                IconButton(onClick = { }) {
                    Icon(painter = painterResource(id = R.drawable.ic_info), contentDescription = "Information", tint = LendlyColors.Content.Primary)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))


            Column(modifier = Modifier.fillMaxWidth().padding(horizontal = LendlySpacing.Spacing4)) {
                Text(text = "Create your password", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = LendlyColors.Content.Primary, modifier = Modifier.padding(bottom = 24.dp))
                Text(text = "Choose a password", color = LendlyColors.Content.Secondary, fontSize = 14.sp, modifier = Modifier.padding(bottom = 8.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("********", color = LendlyColors.Content.Tertiary) },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                painter = painterResource(id = if (passwordVisible) android.R.drawable.ic_menu_view else R.drawable.ic_eye_hidden),
                                contentDescription = null,
                                tint = LendlyColors.Content.Secondary
                            )
                        }
                    },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = LendlyColors.Interactive.Primary,
                        unfocusedBorderColor = LendlyColors.Interactive.Secondary,
                        focusedContainerColor = LendlyColors.Background.Screen,
                        unfocusedContainerColor = LendlyColors.Background.Screen
                    )
                )

                val state = registerState
                if (state is RegisterState.Error) {
                    Text(
                        text = stringResource(id = state.messageRes),
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


        Column(modifier = Modifier.fillMaxWidth()) {
            LendlyBottomAction(
                text = if (registerState is RegisterState.Loading) "Cargando..." else "Next",
                onClick = {
                    if (password.isBlank()) {
                        localError = "Ingresá una contraseña"
                    } else {
                        localError = null
                        viewModel.register(password)
                    }
                }
            )
        }
    }
}
