package com.example.parcial_grupo_4.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcial_grupo_4.ui.theme.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun PantallaSmsVerification(onNext: () -> Unit, onBack: () -> Unit) {
    // Necesitamos 6 variables, una para cada casillero del código
    var code1 by remember { mutableStateOf("") }
    var code2 by remember { mutableStateOf("") }
    var code3 by remember { mutableStateOf("") }
    var code4 by remember { mutableStateOf("") }
    var code5 by remember { mutableStateOf("") }
    var code6 by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LendlyColors.Background.Screen)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // --- PARTE SUPERIOR: Volver y Textos ---
        Column(modifier = Modifier.fillMaxWidth()) {
            TextButton(onClick = onBack, modifier = Modifier.align(Alignment.Start)) {
                Text("< Back", color = LendlyColors.Content.Primary)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Enter the code",
                fontSize = 24.sp,
                color = LendlyColors.Content.Primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Enter the security code we sent to *******731",
                fontSize = 14.sp,
                color = LendlyColors.Content.Secondary
            )
        }

        // --- PARTE CENTRAL: Los 6 casilleros del código ---
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Your Phone Number",
                color = LendlyColors.Content.Primary,
                modifier = Modifier.align(Alignment.Start).padding(bottom = 12.dp)
            )

            // Fila que contiene los 6 mini-inputs
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                val modifierCasillero = Modifier.weight(1f)

                // Generamos los 6 campos individuales
                MiniCasilleroCodigo(value = code1, onValueChange = { if(it.length <= 1) code1 = it }, modifier = modifierCasillero)
                MiniCasilleroCodigo(value = code2, onValueChange = { if(it.length <= 1) code2 = it }, modifier = modifierCasillero)
                MiniCasilleroCodigo(value = code3, onValueChange = { if(it.length <= 1) code3 = it }, modifier = modifierCasillero)
                MiniCasilleroCodigo(value = code4, onValueChange = { if(it.length <= 1) code4 = it }, modifier = modifierCasillero)
                MiniCasilleroCodigo(value = code5, onValueChange = { if(it.length <= 1) code5 = it }, modifier = modifierCasillero)
                MiniCasilleroCodigo(value = code6, onValueChange = { if(it.length <= 1) code6 = it }, modifier = modifierCasillero)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // El texto de reenvío en color verde/link
            TextButton(onClick = { /* No hace nada por ahora */ }) {
                Text(
                    text = "Didn't received a code?",
                    color = LendlyColors.Content.Link,
                    fontSize = 14.sp
                )
            }
        }

        // --- PARTE INFERIOR: Botón Next ---
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

// Un componente auxiliar para no repetir código por cada casillero
@Composable
fun MiniCasilleroCodigo(value: String, onValueChange: (String) -> Unit, modifier: Modifier) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
        singleLine = true,
        // Forzamos a que el teclado que se abra sea SÓLO de números
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}