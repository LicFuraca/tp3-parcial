package com.example.parcial_grupo_4.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcial_grupo_4.R
import com.example.parcial_grupo_4.ui.theme.*
import com.example.parcial_grupo_4.ui.common.LendlyBottomAction

@Composable
fun PantallaFaceRecognition(onNext: () -> Unit, onBack: () -> Unit) {
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
                    text = "Put your face in the\nframe",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 36.sp,
                    color = LendlyColors.Content.Primary,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                Text(
                    text = "Follow these instruction, and let us get\nyou onboarded.",
                    fontSize = 15.sp,
                    lineHeight = 22.sp,
                    color = LendlyColors.Content.Secondary
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Image(
                painter = painterResource(id = R.drawable.img_face_frame),
                contentDescription = "Face Frame",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.2f),
                contentScale = ContentScale.Crop
            )
        }

        LendlyBottomAction(text = "Next", onClick = onNext)
    }
}