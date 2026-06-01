package com.example.parcial_grupo_4.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcial_grupo_4.ui.theme.Inter
import com.example.parcial_grupo_4.ui.theme.LendlyColors

private val ChipHeight = 32.dp
private val ChipMinWidth = 50.dp
private val ChipShape = RoundedCornerShape(8.dp)

private val ChipTextStyle = TextStyle(
    fontFamily = Inter,
    fontWeight = FontWeight.SemiBold,
    fontSize = 14.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.1.sp,
    textAlign = TextAlign.Center,
)

@Composable
fun LendlyFilterChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val backgroundColor = if (selected) LendlyColors.Interactive.Accent else Color.Transparent
    val contentColor = if (selected) LendlyColors.Content.Strong else LendlyColors.Content.Tertiary
    val borderColor = if (selected) LendlyColors.Interactive.Accent else LendlyColors.Content.Tertiary

    Box(
        modifier = modifier
            .defaultMinSize(minWidth = ChipMinWidth)
            .height(ChipHeight)
            .background(color = backgroundColor, shape = ChipShape)
            .border(width = 1.dp, color = borderColor, shape = ChipShape)
            .selectable(selected = selected, onClick = onClick, role = Role.Tab)
            .padding(horizontal = 16.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = text, style = ChipTextStyle, color = contentColor)
    }
}
