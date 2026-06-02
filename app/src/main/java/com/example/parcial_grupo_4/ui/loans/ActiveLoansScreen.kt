package com.example.parcial_grupo_4.ui.loans

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import com.example.parcial_grupo_4.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.parcial_grupo_4.data.model.LoanItem
import com.example.parcial_grupo_4.ui.theme.LendlyColors
import com.example.parcial_grupo_4.ui.theme.LendlySpacing
import com.example.parcial_grupo_4.ui.theme.Montserrat
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActiveLoansScreen(
    viewModel: LoansViewModel,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val loansState by viewModel.loansState.observeAsState(LoansUiState.Idle)

    Column(modifier = modifier.fillMaxSize()) {
        TopAppBar(
            windowInsets = WindowInsets(0),
            colors = TopAppBarDefaults.topAppBarColors(containerColor = LendlyColors.Background.Screen),
            title = {},
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, stringResource(R.string.cd_back), tint = LendlyColors.Content.Primary)
                }
            },
            actions = {
                IconButton(onClick = {}) {
                    Icon(Icons.Filled.DateRange, stringResource(R.string.cd_calendar), tint = LendlyColors.Content.Primary)
                }
            },
        )

        when (val state = loansState) {
            is LoansUiState.Loading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = LendlyColors.Interactive.Primary)
                }
            }
            is LoansUiState.Error -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(state.message, style = MaterialTheme.typography.bodyMedium, color = LendlyColors.Sentiment.Negative)
                        Spacer(Modifier.height(LendlySpacing.Spacing2))
                        OutlinedButton(onClick = { viewModel.loadLoans() }) { Text(stringResource(R.string.active_loans_retry)) }
                    }
                }
            }
            is LoansUiState.Success -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = LendlySpacing.Spacing3, vertical = LendlySpacing.Spacing3),
                ) {
                    item {
                        Text(
                            "Active loans",
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.headlineSmall,
                            color = LendlyColors.Content.Primary,
                        )
                        Spacer(Modifier.height(LendlySpacing.Spacing3))
                    }

                    if (state.data.active.isNotEmpty()) {
                        item {
                            Text("Present", style = MaterialTheme.typography.labelMedium, color = LendlyColors.Content.Tertiary)
                            Spacer(Modifier.height(LendlySpacing.Spacing1))
                        }
                        items(state.data.active) { loan ->
                            ActiveLoanRow(loan)
                            HorizontalDivider(color = LendlyColors.Border.Neutral, thickness = 0.5.dp)
                        }
                        item { Spacer(Modifier.height(LendlySpacing.Spacing3)) }
                    }

                    if (state.data.history.isNotEmpty()) {
                        item {
                            Text("Recent Loans", style = MaterialTheme.typography.labelMedium, color = LendlyColors.Content.Tertiary)
                            Spacer(Modifier.height(LendlySpacing.Spacing1))
                        }
                        items(state.data.history) { loan ->
                            RecentLoanRow(loan)
                            HorizontalDivider(color = LendlyColors.Border.Neutral, thickness = 0.5.dp)
                        }
                    }
                }
            }
            else -> Unit
        }
    }
}

// ─── Active Loan Row ──────────────────────────────────────────────────────────

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun ActiveLoanRow(loan: LoanItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = LendlySpacing.Spacing2),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(LendlySpacing.Spacing2),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f),
        ) {
            // Logo del lender via Glide
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(LendlyColors.Background.Neutral),
                contentAlignment = Alignment.Center,
            ) {
                if (!loan.imageUrl.isNullOrBlank()) {
                    GlideImage(
                        model = loan.imageUrl,
                        contentDescription = loan.lender,
                        modifier = Modifier.size(28.dp),
                        contentScale = ContentScale.Fit,
                    )
                } else {
                    Text(
                        text = loan.lender.firstOrNull()?.toString() ?: "?",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = LendlyColors.Interactive.Primary,
                    )
                }
            }

            Column {
                Text(loan.lender, style = MaterialTheme.typography.labelSmall, color = LendlyColors.Content.Tertiary)
                Text(loan.name.ifBlank { loan.lender }, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium, color = LendlyColors.Content.Primary)
            }
        }

        Column(horizontalAlignment = Alignment.End) {
            loan.fees?.let {
                Text(it, style = MaterialTheme.typography.labelSmall, color = LendlyColors.Content.Tertiary)
            }
            Text(
                formatAmount(loan.amount),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = LendlyColors.Content.Primary,
            )
        }
    }
}

// ─── Recent Loan Row ──────────────────────────────────────────────────────────

@Composable
private fun RecentLoanRow(loan: LoanItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = LendlySpacing.Spacing2),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(LendlySpacing.Spacing2),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f),
        ) {
            Icon(
                Icons.Filled.CheckCircle,
                contentDescription = stringResource(R.string.cd_paid),
                tint = LendlyColors.Sentiment.Positive,
                modifier = Modifier.size(24.dp),
            )
            Column {
                Text(loan.date, style = MaterialTheme.typography.labelSmall, color = LendlyColors.Content.Tertiary)
                Text(loan.lender, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium, color = LendlyColors.Content.Primary)
                Text(loan.name.ifBlank { loan.lender }, style = MaterialTheme.typography.labelSmall, color = LendlyColors.Content.Tertiary)
            }
        }
        Text(stringResource(R.string.active_loans_paid), style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.SemiBold, color = LendlyColors.Sentiment.Positive)
    }
}

// ─── Helpers ──────────────────────────────────────────────────────────────────

private fun formatAmount(amount: Double): String {
    val symbols = DecimalFormatSymbols(Locale.US)
    val df = DecimalFormat("#,##0.00", symbols)
    return "${df.format(amount)} PHP"
}
