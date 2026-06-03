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
import com.example.parcial_grupo_4.ui.theme.LendlyColors
import androidx.compose.material3.OutlinedTextField


private val ScreenBackground = LendlyColors.Background.Soft
private val CardShape = RoundedCornerShape(12.dp)

private data class OnlineCashInOption(
    val titleRes: Int,
    val imageRes: Int,
)

private val BankOptions = listOf(
    OnlineCashInOption(R.string.online_cash_in_bank_bpi, R.drawable.img_bank_bpi),
    OnlineCashInOption(R.string.online_cash_in_bank_chinabank, R.drawable.img_bank_china),
    OnlineCashInOption(R.string.online_cash_in_bank_rcbc, R.drawable.img_bank_rcbc),
    OnlineCashInOption(R.string.online_cash_in_bank_unionbank, R.drawable.img_bank_union),
)

private val WalletOptions = listOf(
    OnlineCashInOption(R.string.online_cash_in_wallet_gcash, R.drawable.img_ewallet_gcash),
    OnlineCashInOption(R.string.online_cash_in_wallet_paymaya, R.drawable.img_ewallet_paymaya),
    OnlineCashInOption(R.string.online_cash_in_wallet_paypal, R.drawable.img_ewallet_paypal),
)

@Composable
fun OnlineCashInScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onOptionClick: () -> Unit = {},
) {
    val searchText = remember { mutableStateOf("") }
    val query = searchText.value.trim()

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
            OnlineOptionSection(
                title = stringResource(R.string.online_cash_in_banks_section),
                options = BankOptions.filterByQuery(query),
                onOptionClick = onOptionClick,
            )

            HorizontalDivider(
                thickness = 1.dp,
                color = LendlyColors.Border.Subtle,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            OnlineOptionSection(
                title = stringResource(R.string.online_cash_in_wallets_section),
                options = WalletOptions.filterByQuery(query),
                onOptionClick = onOptionClick,
            )
        }
    }
}

@Composable
private fun OnlineSearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
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
private fun OnlineOptionSection(
    title: String,
    options: List<OnlineCashInOption>,
    onOptionClick: () -> Unit,
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium,
            color = LendlyColors.Content.Primary,
            fontWeight = FontWeight.Medium,
        )

        options.forEach { option ->
            HomeOptionItem(
                title = stringResource(option.titleRes),
                leadingImageRes = option.imageRes,
                onClick = onOptionClick,
            )
        }
    }
}

@Composable
private fun List<OnlineCashInOption>.filterByQuery(query: String): List<OnlineCashInOption> {
    if (query.isBlank()) return this

    return filter { option ->
        stringResource(option.titleRes).contains(query, ignoreCase = true)
    }
}
