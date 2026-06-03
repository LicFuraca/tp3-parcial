package com.example.parcial_grupo_4.ui.home.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.parcial_grupo_4.R

private val TopBarHeight = 64.dp
private val ActionSize = 48.dp

@Composable
fun HomeTopBar(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    title: String? = null,
    @DrawableRes trailingIconRes: Int? = null,
    onTrailingClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(TopBarHeight)
            .padding(top = 8.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .size(ActionSize)
                .offset(x = (-12).dp),
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_arrow_back),
                contentDescription = stringResource(R.string.cash_in_back),
                tint = Color.Black,
            )
        }

        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center,
        ) {
            if (title != null) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFF1D1B20),
                    textAlign = TextAlign.Center,
                )
            }
        }

        Box(
            modifier = Modifier.size(ActionSize),
            contentAlignment = Alignment.Center,
        ) {
            if (trailingIconRes != null) {
                IconButton(onClick = onTrailingClick) {
                    Icon(
                        painter = painterResource(trailingIconRes),
                        contentDescription = null,
                        tint = Color.Black,
                    )
                }
            }
        }
    }
}