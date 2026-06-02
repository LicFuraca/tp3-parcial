package com.example.parcial_grupo_4.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.parcial_grupo_4.ui.home.components.HomeOptionItem
import androidx.compose.ui.res.stringResource
import com.example.parcial_grupo_4.R
import androidx.compose.material3.HorizontalDivider
import com.example.parcial_grupo_4.ui.home.components.HomeTopBar


private val ScreenBackground = Color(0xFFFCF8F8)
private val CardShape = RoundedCornerShape(12.dp)

@Composable
fun OnlineCashInScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
) {
    val searchText = remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ScreenBackground)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        HomeTopBar(onBackClick = onBackClick)

        Text(
            text = stringResource(R.string.online_cash_in_title),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
        )

        OnlineSearchBar(
            value = searchText.value,
            onValueChange = { searchText.value = it },
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, CardShape)
                .padding(vertical = 16.dp),
        ) {
            OnlineBankSection()

            HorizontalDivider(
                thickness = 1.dp,
                color = Color(0xFFE5E2E1),
                modifier = Modifier.padding(vertical = 16.dp)
            )

            OnlineWalletSection()
        }
    }
}

@Composable
private fun OnlineSearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    androidx.compose.material3.OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        placeholder = {
            Text(stringResource(R.string.online_cash_in_search_placeholder))
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = null,
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(8.dp),
    )
}

@Composable
private fun OnlineBankSection() {
    OnlineOptionSection(
        title = stringResource(R.string.online_cash_in_banks_section),
        items = listOf(
            stringResource(R.string.online_cash_in_bank_bpi),
            stringResource(R.string.online_cash_in_bank_chinabank),
            stringResource(R.string.online_cash_in_bank_rcbc),
            stringResource(R.string.online_cash_in_bank_unionbank),
        ),
        icon = Icons.Outlined.AccountBalance,
    )
}

@Composable
private fun OnlineWalletSection() {
    OnlineOptionSection(
        title = stringResource(R.string.online_cash_in_wallets_section),
        items = listOf(
            stringResource(R.string.online_cash_in_wallet_gcash),
            stringResource(R.string.online_cash_in_wallet_paymaya),
            stringResource(R.string.online_cash_in_wallet_paypal),
        ),
        icon = Icons.Outlined.AccountBalanceWallet,
    )
}

@Composable
private fun OnlineOptionSection(
    title: String,
    items: List<String>,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium,
            color = Color.Black,
            fontWeight = FontWeight.Medium,
        )

        items.forEach { item ->
            HomeOptionItem(
                title = item,
                leadingIcon = icon,
            )
        }
    }
}