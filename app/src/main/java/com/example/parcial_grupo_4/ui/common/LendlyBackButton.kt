package com.example.parcial_grupo_4.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.parcial_grupo_4.R
import com.example.parcial_grupo_4.ui.theme.LendlyColors

private val ButtonSize = 40.dp
private val IconSize = 20.dp
private val DefaultBackground = Color(0xFFE7E7E7)

@Composable
fun LendlyBackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    background: Color = DefaultBackground,
) {
    Box(
        modifier = modifier
            .size(ButtonSize)
            .background(color = background, shape = CircleShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
            contentDescription = stringResource(R.string.back),
            tint = LendlyColors.Content.Primary,
            modifier = Modifier.size(IconSize),
        )
    }
}
