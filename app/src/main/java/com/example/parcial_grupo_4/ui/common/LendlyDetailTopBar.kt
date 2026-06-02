package com.example.parcial_grupo_4.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.parcial_grupo_4.R
import com.example.parcial_grupo_4.ui.theme.LendlyColors

private val TopBarHeight = 56.dp
private val HeaderIconSize = 24.dp

@Composable
fun LendlyDetailTopBar(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    onInfoClick: () -> Unit = {},
    onMoreClick: () -> Unit = {},
    background: Color = LendlyColors.Background.Icon,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(background)
            .statusBarsPadding()
            .height(TopBarHeight)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        LendlyBackButton(onClick = onBackClick)
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Outlined.Info,
            contentDescription = stringResource(R.string.info),
            tint = LendlyColors.Content.Primary,
            modifier = Modifier
                .size(HeaderIconSize)
                .clickable(onClick = onInfoClick),
        )
        Icon(
            imageVector = Icons.Outlined.MoreHoriz,
            contentDescription = stringResource(R.string.more_options),
            tint = LendlyColors.Content.Primary,
            modifier = Modifier
                .size(HeaderIconSize)
                .clickable(onClick = onMoreClick),
        )
    }
}
