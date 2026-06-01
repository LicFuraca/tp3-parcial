package com.example.parcial_grupo_4.ui.auth

import androidx.compose.foundation.Image
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcial_grupo_4.R
import com.example.parcial_grupo_4.ui.theme.*
import com.example.parcial_grupo_4.ui.common.LendlyBottomAction

@Composable
fun PantallaVerifiedPage(onNext: () -> Unit, onBack: () -> Unit) {
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

            Image(
                painter = painterResource(id = R.drawable.ic_verified_shield),
                contentDescription = "Verified Shield",
                modifier = Modifier.size(130.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LendlySpacing.Spacing4),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Woah, Your face and ID\nare the same!",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 36.sp,
                    textAlign = TextAlign.Center,
                    color = LendlyColors.Content.Primary,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = "We are just a few questions away from\nopening your own lendly loan account. Tap\nthe button to continue.",
                    fontSize = 15.sp,
                    lineHeight = 22.sp,
                    textAlign = TextAlign.Center,
                    color = LendlyColors.Content.Secondary
                )

                Spacer(modifier = Modifier.height(32.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Security Guard",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = LendlyColors.Content.Primary,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )
                        Text(
                            text = "Our online security feature world-class\nprotection against hackers. It makes\nthem cry and rethink their purpose\nin life.",
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            textAlign = TextAlign.Center,
                            color = LendlyColors.Content.Secondary
                        )
                    }
                }
            }
        }

        LendlyBottomAction(text = "Next", onClick = onNext)
    }
}