package com.example.parcial_grupo_4.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
private val CardShape = RoundedCornerShape(12.dp)

@Composable
fun OverTheCounterCashInScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onPartnerClick: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ScreenBackground)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
    ) {
        HomeTopBar(onBackClick = onBackClick)

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

            OverCounterPartnersCard(onPartnerClick = onPartnerClick)
        }
    }
}

@Composable
private fun OverCounterPartnersCard(
    onPartnerClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White, CardShape)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        HomeOptionItem(
            title = stringResource(R.string.over_counter_partner_7_eleven),
            description = stringResource(R.string.over_counter_max_transaction),
            leadingImageRes = R.drawable.img_partner_7eleven,
            onClick = onPartnerClick,
        )

        HomeOptionItem(
            title = stringResource(R.string.over_counter_partner_cebuana),
            description = stringResource(R.string.over_counter_max_transaction),
            leadingImageRes = R.drawable.img_partner_cebuana,
            onClick = onPartnerClick,
        )

        HomeOptionItem(
            title = stringResource(R.string.over_counter_partner_lbc),
            description = stringResource(R.string.over_counter_max_transaction),
            leadingImageRes = R.drawable.img_partner_lbc,
            onClick = onPartnerClick,
        )

        HomeOptionItem(
            title = stringResource(R.string.over_counter_partner_m_lhuillier),
            description = stringResource(R.string.over_counter_max_transaction),
            leadingImageRes = R.drawable.img_partner_lhuillier,
            onClick = onPartnerClick,
        )
    }
}

