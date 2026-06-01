package com.example.parcial_grupo_4.ui.history

import androidx.annotation.StringRes
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowUpward
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.parcial_grupo_4.R
import com.example.parcial_grupo_4.ui.common.LendlyFilterChip
import com.example.parcial_grupo_4.ui.common.LendlySearchBar
import com.example.parcial_grupo_4.ui.theme.LendlyColors

private enum class HistoryFilter(@param:StringRes val labelRes: Int) {
    All(R.string.history_filter_all),
    Type(R.string.history_filter_type),
    Balance(R.string.history_filter_balance),
    PaidBills(R.string.history_filter_paid_bills),
    Added(R.string.history_filter_added),
}

private data class TransactionSection(
    @param:StringRes val titleRes: Int,
    val items: List<TransactionUiModel>,
)

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    onTransactionClick: (TransactionUiModel) -> Unit = {},
) {
    var query by rememberSaveable { mutableStateOf("") }
    var selectedFilter by rememberSaveable { mutableStateOf(HistoryFilter.All) }

    val sections = remember { sampleHistorySections() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = stringResource(R.string.history_title),
            style = MaterialTheme.typography.headlineMedium,
            color = LendlyColors.Content.Primary,
            modifier = Modifier.padding(top = 16.dp),
        )

        LendlySearchBar(value = query, onValueChange = { query = it })

        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            HistoryFilter.entries.forEach { filter ->
                LendlyFilterChip(
                    text = stringResource(filter.labelRes),
                    selected = filter == selectedFilter,
                    onClick = { selectedFilter = filter },
                )
            }
        }

        LazyColumn(
            contentPadding = PaddingValues(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            sections.forEach { section ->
                item(key = "section-${section.titleRes}") {
                    Text(
                        text = stringResource(section.titleRes),
                        style = MaterialTheme.typography.labelLarge,
                        color = LendlyColors.Content.Tertiary,
                        modifier = Modifier.padding(top = 8.dp, bottom = 4.dp),
                    )
                }
                items(items = section.items, key = { it.id }) { transaction ->
                    TransactionItem(
                        transaction = transaction,
                        onClick = { onTransactionClick(transaction) },
                    )
                }
            }
        }
    }
}

private fun sampleHistorySections(): List<TransactionSection> = listOf(
    TransactionSection(
        titleRes = R.string.history_section_today,
        items = listOf(
            TransactionUiModel("t1", "Paid this month", "Apple Inc.", "9:07 AM", "1,2555 PHP", Icons.Outlined.ArrowUpward),
            TransactionUiModel("t2", "Paid this month", "Apple Inc.", "9:07 AM", "1,2555 PHP", Icons.Outlined.ArrowUpward),
            TransactionUiModel("t3", "Paid this month", "Apple Inc.", "9:07 AM", "1,2555 PHP", Icons.Outlined.ArrowUpward),
            TransactionUiModel("t4", "Added", "Apple Inc.", "9:07 AM", "1,200 PHP", Icons.Outlined.Add),
            TransactionUiModel("t5", "Paid this month", "Apple Inc.", "9:07 AM", "1,200 PHP", Icons.Outlined.Add),
        ),
    ),
    TransactionSection(
        titleRes = R.string.history_section_recent_loans,
        items = listOf(
            TransactionUiModel("r1", "iPhone 15 Pro Max", "Apple Inc.", "02/08/2024", "Paid", Icons.Outlined.Check),
            TransactionUiModel("r2", "iPhone 15 Pro Max", "Apple Inc.", "02/08/2024", "Paid", Icons.Outlined.Check),
            TransactionUiModel("r3", "iPhone 15 Pro Max", "Apple Inc.", "02/08/2024", "Paid", Icons.Outlined.Check),
        ),
    ),
)
