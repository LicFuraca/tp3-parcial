package com.example.parcial_grupo_4.ui.home.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.parcial_grupo_4.ui.theme.LendlyColors
import com.example.parcial_grupo_4.R

private val OptionItemMinHeight = 52.dp
private val LeadingContainerSize = 40.dp
private val LeadingIconSize = 18.dp
private val LeadingImageSize = 40.dp
private val TrailingArrowWidth = 12.dp
private val TrailingArrowHeight = 12.dp
private val LeadingContainerColor = LendlyColors.Background.Neutral

@Composable
fun HomeOptionItem(
    title: String,
    modifier: Modifier = Modifier,
    description: String? = null,
    leadingIcon: ImageVector? = null,
    @DrawableRes leadingIconRes: Int? = null,
    @DrawableRes leadingImageRes: Int? = null,
    onClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = OptionItemMinHeight)
            .clickable(onClick = onClick)
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        LeadingOptionVisual(
            leadingIcon = leadingIcon,
            leadingIconRes = leadingIconRes,
            leadingImageRes = leadingImageRes,
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
            )

            if (description != null) {
                Text(
                    text = description,
                    style = MaterialTheme.typography.labelMedium,
                    color = LendlyColors.Content.Tertiary,
                )
            }
        }

        Icon(
            painter = painterResource(R.drawable.ic_mini_arrow_right),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(
                width = TrailingArrowWidth,
                height = TrailingArrowHeight,
            ),
        )
    }
}

@Composable
private fun LeadingOptionVisual(
    leadingIcon: ImageVector?,
    @DrawableRes leadingIconRes: Int?,
    @DrawableRes leadingImageRes: Int?,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.size(LeadingContainerSize),
        contentAlignment = Alignment.Center,
    ) {
        when {
            leadingImageRes != null -> {
                Image(
                    painter = painterResource(leadingImageRes),
                    contentDescription = null,
                    modifier = Modifier.size(LeadingImageSize),
                )
            }

            leadingIconRes != null -> {
                Box(
                    modifier = Modifier
                        .size(LeadingContainerSize)
                        .background(LeadingContainerColor, CircleShape),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        painter = painterResource(leadingIconRes),
                        contentDescription = null,
                        tint = LendlyColors.Content.Primary,
                        modifier = Modifier.size(LeadingIconSize),
                    )
                }
            }

            leadingIcon != null -> {
                Box(
                    modifier = Modifier
                        .size(LeadingContainerSize)
                        .background(LeadingContainerColor, CircleShape),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = null,
                        tint = LendlyColors.Content.Primary,
                        modifier = Modifier.size(LeadingIconSize),
                    )
                }
            }
        }
    }
}