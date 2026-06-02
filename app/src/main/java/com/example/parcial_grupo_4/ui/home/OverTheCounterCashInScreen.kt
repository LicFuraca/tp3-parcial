package com.example.parcial_grupo_4.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Store
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.parcial_grupo_4.ui.home.components.HomeOptionItem

private val ScreenBackground = Color(0xFFFCF8F8)
private val CardShape = RoundedCornerShape(12.dp)

@Composable
fun OverTheCounterCashInScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ScreenBackground)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
    ) {
        OverCounterTopBar(onBackClick = onBackClick)

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(32.dp),
        ) {
            Text(
                text = stringResource(R.string.over_counter_title),
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
            )

            OverCounterPartnersCard()
        }
    }
}

@Composable
private fun OverCounterTopBar(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .padding(start = 4.dp, top = 8.dp, end = 4.dp, bottom = 8.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                contentDescription = stringResource(R.string.cash_in_back),
                tint = Color.Black,
            )
        }
    }
}

@Composable
private fun OverCounterPartnersCard(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White, CardShape)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        OverCounterPartnerItem(title = stringResource(R.string.over_counter_partner_7_eleven))
        OverCounterPartnerItem(title = stringResource(R.string.over_counter_partner_cebuana))
        OverCounterPartnerItem(title = stringResource(R.string.over_counter_partner_lbc))
        OverCounterPartnerItem(title = stringResource(R.string.over_counter_partner_m_lhuillier))
    }
}

@Composable
private fun OverCounterPartnerItem(
    title: String,
    modifier: Modifier = Modifier,
) {
    HomeOptionItem(
        title = title,
        description = stringResource(R.string.over_counter_max_transaction),
        leadingIcon = Icons.Outlined.Store,
        modifier = modifier,
    )
}