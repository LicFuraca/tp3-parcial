package com.example.parcial_grupo_4.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.parcial_grupo_4.R
import com.example.parcial_grupo_4.ui.home.components.HomeTopBar
import com.example.parcial_grupo_4.ui.home.components.NotificationItem
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.example.parcial_grupo_4.ui.home.components.NotificationCalendar
import androidx.compose.foundation.layout.Box
import com.example.parcial_grupo_4.ui.theme.LendlyColors


private val ScreenBackground = Color.White
private val DividerColor = LendlyColors.Border.Subtle

@Composable
fun NotificationScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
) {
    var showCalendar by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ScreenBackground)
            .padding(horizontal = 16.dp)
            .padding(bottom = 48.dp),
    ) {
        HomeTopBar(
            onBackClick = onBackClick,
            trailingIconRes = R.drawable.ic_calendar,
            onTrailingClick = {
                showCalendar = true
            },
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(32.dp),
        ) {
            Text(
                text = stringResource(R.string.notification_title),
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
            )

            NotificationSection(
                title = stringResource(R.string.notification_today),
                items = listOf(true, true, false, false),
            )

            NotificationSection(
                title = stringResource(R.string.notification_announcement),
                items = listOf(true, false),
            )
        }
    }

    if (showCalendar) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.10f))
                .padding(horizontal = 16.dp)
                .padding(top = 56.dp),
            contentAlignment = Alignment.TopCenter,
        ) {
            NotificationCalendar(
                onOkClick = {
                    showCalendar = false
                },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }

}

@Composable
private fun NotificationSection(
    title: String,
    items: List<Boolean>,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = LendlyColors.Content.Tertiary,
                fontWeight = FontWeight.Medium,
            )

            HorizontalDivider(
                thickness = 1.dp,
                color = DividerColor,
            )
        }

        items.forEachIndexed { index, active ->
            NotificationItem(
                title = stringResource(
                    if (index % 2 == 0) {
                        R.string.notification_due_title
                    } else {
                        R.string.notification_help_title
                    }
                ),
                body = stringResource(R.string.notification_body),
                date = stringResource(R.string.notification_date),
                active = active,
            )
        }
    }
}