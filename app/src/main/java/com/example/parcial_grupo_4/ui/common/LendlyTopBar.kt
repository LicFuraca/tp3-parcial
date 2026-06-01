package com.example.parcial_grupo_4.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.example.parcial_grupo_4.R
import com.example.parcial_grupo_4.ui.theme.LendlyColors

private val TopBarHeight = 56.dp
private val SideContainerSize = 40.dp
private val AvatarIconSize = DpSize(17.dp, 16.dp)
private val NotificationsIconSize = DpSize(15.dp, 20.dp)
private val MainIconSize = DpSize(59.dp, 20.dp)

@Composable
fun LendlyTopBar(
    modifier: Modifier = Modifier,
    onProfileClick: () -> Unit = {},
    onNotificationsClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(LendlyColors.Background.Screen)
            .statusBarsPadding()
            .height(TopBarHeight),
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 4.dp, top = 8.dp)
                .size(SideContainerSize)
                .clickable(onClick = onProfileClick),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_topbar_avatar),
                contentDescription = stringResource(R.string.topbar_profile),
                tint = LendlyColors.Content.Primary,
                modifier = Modifier.size(AvatarIconSize),
            )
        }

        Icon(
            painter = painterResource(R.drawable.ic_topbar_main_icon),
            contentDescription = stringResource(R.string.topbar_app_logo),
            tint = Color.Unspecified,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 22.dp)
                .size(MainIconSize),
        )

        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 4.dp, top = 8.dp)
                .size(SideContainerSize)
                .clickable(onClick = onNotificationsClick),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_topbar_notifications),
                contentDescription = stringResource(R.string.topbar_notifications),
                tint = LendlyColors.Content.Primary,
                modifier = Modifier.size(NotificationsIconSize),
            )
        }
    }
}
