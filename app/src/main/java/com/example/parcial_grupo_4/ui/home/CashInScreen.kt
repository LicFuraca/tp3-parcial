package com.example.parcial_grupo_4.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.parcial_grupo_4.R
import com.example.parcial_grupo_4.ui.theme.LendlyColors

private val ScreenBackground = Color(0xFFFCF8F8)
private val ContentHorizontalPadding = 16.dp
private val TopBarHeight = 64.dp
private val OptionsCardShape = RoundedCornerShape(12.dp)
private val OptionsCardColor = Color.White
private val IconContainerColor = Color(0xFFE5F5EA)
private val IconContainerSize = 40.dp
private val IconSize = 20.dp

@Composable
fun CashInScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onInfoClick: () -> Unit = {},
    onOnlineBankingClick: () -> Unit = {},
    onOverTheCounterClick: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ScreenBackground),
    ) {
        CashInTopBar(
            onBackClick = onBackClick,
            onInfoClick = onInfoClick,
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = ContentHorizontalPadding),
            verticalArrangement = Arrangement.spacedBy(32.dp),
        ) {
            Text(
                text = stringResource(R.string.cash_in_options_title),
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
            )

            CashInOptionsCard(
                onOnlineBankingClick = onOnlineBankingClick,
                onOverTheCounterClick = onOverTheCounterClick,
            )
        }
    }
}

@Composable
private fun CashInTopBar(
    onBackClick: () -> Unit,
    onInfoClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(TopBarHeight)
            .padding(start = 4.dp, top = 8.dp, end = 4.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clickable(onClick = onBackClick),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                contentDescription = stringResource(R.string.cash_in_back),
                tint = Color.Black,
            )
        }

        Text(
            text = stringResource(R.string.cash_in_title),
            style = MaterialTheme.typography.titleMedium,
            color = Color(0xFF1D1B20),
            modifier = Modifier.weight(1f),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
        )

        Box(
            modifier = Modifier
                .size(48.dp)
                .clickable(onClick = onInfoClick),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = Icons.Outlined.Info,
                contentDescription = stringResource(R.string.cash_in_info),
                tint = Color.Black,
            )
        }
    }
}

@Composable
private fun CashInOptionsCard(
    onOnlineBankingClick: () -> Unit,
    onOverTheCounterClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(OptionsCardColor, OptionsCardShape)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        CashInOptionItem(
            title = stringResource(R.string.cash_in_online_title),
            description = stringResource(R.string.cash_in_online_description),
            icon = Icons.Outlined.AccountBalanceWallet,
            onClick = onOnlineBankingClick,
        )

        CashInOptionItem(
            title = stringResource(R.string.cash_in_counter_title),
            description = stringResource(R.string.cash_in_counter_description),
            icon = Icons.Outlined.LocationOn,
            onClick = onOverTheCounterClick,
        )
    }
}

@Composable
private fun CashInOptionItem(
    title: String,
    description: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .clickable(onClick = onClick)
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Box(
            modifier = Modifier
                .size(IconContainerSize)
                .background(IconContainerColor, CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = LendlyColors.Content.Primary,
                modifier = Modifier.size(IconSize),
            )
        }

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

            Text(
                text = description,
                style = MaterialTheme.typography.labelMedium,
                color = LendlyColors.Content.Tertiary,
            )
        }

        Icon(
            imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
            contentDescription = null,
            tint = LendlyColors.Content.Primary,
        )
    }
}