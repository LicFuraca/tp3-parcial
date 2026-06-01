package com.example.parcial_grupo_4.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.parcial_grupo_4.ui.theme.LendlyColors
import com.example.parcial_grupo_4.ui.theme.LendlySpacing

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PromotionalBanner(
    title: String,
    subtitle: String,
    buttonText: String,
    imageUrl: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(LendlySpacing.Spacing3),
        colors = CardDefaults.cardColors(containerColor = LendlyColors.Interactive.Primary),
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp),
        onClick = onClick
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            GlideImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .fillMaxHeight()
                    .width(180.dp),
                contentScale = ContentScale.Fit
            )
            
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(LendlySpacing.Spacing3),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    color = LendlyColors.Contrast.Light,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = subtitle,
                    color = LendlyColors.Contrast.Light.copy(alpha = 0.8f),
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(LendlySpacing.Spacing3))
                Button(
                    onClick = onClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LendlyColors.Interactive.Accent,
                        contentColor = LendlyColors.Interactive.Primary
                    ),
                    shape = RoundedCornerShape(LendlySpacing.Spacing4),
                    contentPadding = PaddingValues(horizontal = LendlySpacing.Spacing4, vertical = 0.dp),
                    modifier = Modifier.height(36.dp)
                ) {
                    Text(
                        text = buttonText,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
