package com.example.parcial_grupo_4.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.parcial_grupo_4.R
import com.example.parcial_grupo_4.ui.common.PrimaryButton
import com.example.parcial_grupo_4.ui.home.components.HomeTopBar

private val ScreenBackground = Color(0xFFFCF8F8)
private val DividerColor = Color(0xFFE5E2E1)

@Composable
fun CashInAmountScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onNextClick: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ScreenBackground)
            .padding(horizontal = 16.dp),
    ) {
        HomeTopBar(onBackClick = onBackClick)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                text = stringResource(R.string.cash_in_amount_title),
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
            )

            AmountContent()
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        )

        PrimaryButton(
            text = stringResource(R.string.cash_in_amount_next),
            onClick = onNextClick,
            modifier = Modifier.padding(bottom = 16.dp),
        )
    }
}

@Composable
private fun AmountContent(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = stringResource(R.string.cash_in_amount_balance),
            style = MaterialTheme.typography.labelLarge,
            color = Color(0xFF6A6C6A),
            fontWeight = FontWeight.SemiBold,
        )

        Text(
            text = stringResource(R.string.cash_in_amount_value),
            style = MaterialTheme.typography.headlineSmall,
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )

        HorizontalDivider(
            thickness = 1.dp,
            color = DividerColor,
        )

        Text(
            text = stringResource(R.string.cash_in_amount_limit),
            style = MaterialTheme.typography.labelLarge,
            color = Color(0xFF6A6C6A),
            fontWeight = FontWeight.SemiBold,
        )
    }
}