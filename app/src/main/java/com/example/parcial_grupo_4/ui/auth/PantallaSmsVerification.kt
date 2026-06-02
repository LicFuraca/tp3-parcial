package com.example.parcial_grupo_4.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcial_grupo_4.R
import com.example.parcial_grupo_4.ui.theme.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import com.example.parcial_grupo_4.ui.common.LendlyBottomAction

@Composable
fun PantallaSmsVerification(onNext: () -> Unit, onBack: () -> Unit) {
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
                    text = "Enter the code",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = LendlyColors.Content.Primary,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Enter the security code we sent to\n******731",
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    color = LendlyColors.Content.Secondary
                )

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "Your Phone Number",
                    color = LendlyColors.Content.Primary,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    val modifierCasillero = Modifier.weight(1f)

                    MiniCasilleroCodigo(value = code1, placeholder = "2", onValueChange = { if(it.length <= 1) code1 = it }, modifier = modifierCasillero)
                    MiniCasilleroCodigo(value = code2, placeholder = "5", onValueChange = { if(it.length <= 1) code2 = it }, modifier = modifierCasillero)
                    MiniCasilleroCodigo(value = code3, placeholder = "4", onValueChange = { if(it.length <= 1) code3 = it }, modifier = modifierCasillero)
                    MiniCasilleroCodigo(value = code4, placeholder = "4", onValueChange = { if(it.length <= 1) code4 = it }, modifier = modifierCasillero)
                    MiniCasilleroCodigo(value = code5, placeholder = "4", onValueChange = { if(it.length <= 1) code5 = it }, modifier = modifierCasillero)
                    MiniCasilleroCodigo(value = code6, placeholder = "4", onValueChange = { if(it.length <= 1) code6 = it }, modifier = modifierCasillero)
                }

                Spacer(modifier = Modifier.height(32.dp))

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Didn’t received a code?",
                        color = LendlyColors.Content.Link,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        textDecoration = TextDecoration.Underline
                    )
                }
            }
        }

        LendlyBottomAction(text = "Next", onClick = onNext)
    }
}

@Composable
fun MiniCasilleroCodigo(value: String, placeholder: String, onValueChange: (String) -> Unit, modifier: Modifier) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center, fontSize = 16.sp),
        placeholder = {
            Text(
                text = placeholder,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = LendlyColors.Content.Tertiary
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = LendlyColors.Interactive.Secondary,
            unfocusedBorderColor = LendlyColors.Interactive.Secondary,
            focusedContainerColor = LendlyColors.Background.Screen,
            unfocusedContainerColor = LendlyColors.Background.Screen
        )
    )
}