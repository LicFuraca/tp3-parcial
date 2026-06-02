package com.example.parcial_grupo_4.ui.history

import androidx.annotation.StringRes
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
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

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    onTransactionClick: (TransactionUiModel) -> Unit = {},
    viewModel: HistoryViewModel = hiltViewModel(),
) {
    val sections by viewModel.sections.observeAsState(emptyList())
    val isLoading by viewModel.isLoading.observeAsState(false)
    val errorMessageRes by viewModel.errorMessageRes.observeAsState()

    var query by rememberSaveable { mutableStateOf("") }
    var selectedFilter by rememberSaveable { mutableStateOf(HistoryFilter.All) }

    val visibleSections = remember(sections, query, selectedFilter) {
        sections.applyFilters(query = query, filter = selectedFilter)
    }

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

        val errorRes = errorMessageRes
        when {
            sections.isEmpty() && isLoading -> CenteredState {
                CircularProgressIndicator(color = LendlyColors.Interactive.Primary)
            }

            sections.isEmpty() && errorRes != null -> CenteredState {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = stringResource(errorRes),
                        style = MaterialTheme.typography.bodyMedium,
                        color = LendlyColors.Content.Tertiary,
                        textAlign = TextAlign.Center,
                    )
                    TextButton(onClick = { viewModel.refresh() }) {
                        Text(text = stringResource(R.string.history_retry))
                    }
                }
            }

            visibleSections.isEmpty() -> CenteredState {
                Text(
                    text = stringResource(R.string.history_no_results),
                    style = MaterialTheme.typography.bodyMedium,
                    color = LendlyColors.Content.Tertiary,
                    textAlign = TextAlign.Center,
                )
            }

            else -> LazyColumn(
                contentPadding = PaddingValues(bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                visibleSections.forEach { section ->
                    item(key = "section-${section.title}") {
                        Text(
                            text = section.title,
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
}

@Composable
private fun CenteredState(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 48.dp),
        contentAlignment = Alignment.TopCenter,
    ) {
        content()
    }
}

private fun List<TransactionSection>.applyFilters(
    query: String,
    filter: HistoryFilter,
): List<TransactionSection> {
    val term = query.trim()
    return mapNotNull { section ->
        val items = section.items.filter { it.matchesQuery(term) && it.matchesFilter(filter) }
        if (items.isEmpty()) null else section.copy(items = items)
    }
}

private fun TransactionUiModel.matchesQuery(term: String): Boolean {
    if (term.isBlank()) return true
    return listOf(title, subtitleCompany, amount, time).any { it.contains(term, ignoreCase = true) }
}

private fun TransactionUiModel.matchesFilter(filter: HistoryFilter): Boolean = when (filter) {
    HistoryFilter.All -> true
    HistoryFilter.PaidBills -> type == "LOAN_PAYMENT"
    HistoryFilter.Added -> type == "CASH_IN" || type == "LOAN_DISBURSEMENT"
    // Sin criterio definido aún con los datos disponibles: se comportan como "All".
    HistoryFilter.Type, HistoryFilter.Balance -> true
}
