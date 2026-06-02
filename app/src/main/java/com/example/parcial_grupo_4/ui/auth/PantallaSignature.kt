package com.example.parcial_grupo_4.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcial_grupo_4.R
import com.example.parcial_grupo_4.ui.theme.*
import com.example.parcial_grupo_4.ui.common.LendlyBottomAction

@Composable
fun PantallaSignature(onNext: () -> Unit, onBack: () -> Unit) {
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
                    text = "Let’s seal the deal!",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 36.sp,
                    color = LendlyColors.Content.Primary,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                Text(
                    text = "You can use your finger or a compatible\nstylus to write you signature",
                    fontSize = 15.sp,
                    lineHeight = 22.sp,
                    color = LendlyColors.Content.Secondary
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Box de la firma
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LendlySpacing.Spacing4)
                    .height(300.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(24.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_signature_pen),
                    contentDescription = "Signature Icon",
                    modifier = Modifier
                        .size(28.dp)
                        .align(Alignment.TopEnd)
                )

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Sign here",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = LendlyColors.Content.Secondary,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = "(same signature as with the\ndocument you provided)",
                        fontSize = 13.sp,
                        lineHeight = 18.sp,
                        textAlign = TextAlign.Center,
                        color = LendlyColors.Content.Tertiary
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "By tapping “Next”, you confirm that the\ninformation you provided is true and correct.",
                fontSize = 14.sp,
                lineHeight = 20.sp,
                textAlign = TextAlign.Center,
                color = LendlyColors.Content.Secondary,
                modifier = Modifier.padding(horizontal = LendlySpacing.Spacing4)
            )
        }

        LendlyBottomAction(text = "Next", onClick = onNext)
    }
}