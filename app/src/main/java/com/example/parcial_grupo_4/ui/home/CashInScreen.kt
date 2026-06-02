package com.example.parcial_grupo_4.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.parcial_grupo_4.R
import com.example.parcial_grupo_4.ui.home.components.HomeOptionItem
import com.example.parcial_grupo_4.ui.home.components.HomeTopBar

private val ScreenBackground = Color(0xFFFCF8F8)
private val OptionsCardShape = RoundedCornerShape(12.dp)
private val OptionsCardColor = Color.White

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
            .background(ScreenBackground)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        HomeTopBar(
            title = stringResource(R.string.cash_in_title),
            trailingIcon = Icons.Outlined.Info,
            onBackClick = onBackClick,
            onTrailingClick = onInfoClick,
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
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
        HomeOptionItem(
            title = stringResource(R.string.cash_in_online_title),
            description = stringResource(R.string.cash_in_online_description),
            leadingIcon = Icons.Outlined.AccountBalanceWallet,
            onClick = onOnlineBankingClick,
        )

        HomeOptionItem(
            title = stringResource(R.string.cash_in_counter_title),
            description = stringResource(R.string.cash_in_counter_description),
            leadingIcon = Icons.Outlined.LocationOn,
            onClick = onOverTheCounterClick,
        )
    }
}

