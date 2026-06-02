package com.example.parcial_grupo_4.ui.loans

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.res.stringResource
import com.example.parcial_grupo_4.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.parcial_grupo_4.data.model.LoanApplication
import com.example.parcial_grupo_4.ui.common.PrimaryButton
import com.example.parcial_grupo_4.ui.navigation.Routes
import com.example.parcial_grupo_4.ui.theme.LendlyColors
import com.example.parcial_grupo_4.ui.theme.LendlySpacing
import com.example.parcial_grupo_4.ui.theme.Montserrat

@Composable
fun LoanSuccessScreen(
    viewModel: LoansViewModel,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val applyState by viewModel.applyState.observeAsState(ApplyUiState.Idle)
    val result = (applyState as? ApplyUiState.Success)?.result

    if (result == null) {
        LaunchedEffect(Unit) { navController.popBackStack() }
        return
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(LendlyColors.Background.Screen),
    ) {
        // Botones superiores
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = LendlySpacing.Spacing1)
                .padding(top = LendlySpacing.Spacing1),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            IconButton(
                onClick = {
                    viewModel.resetForm()
                    navController.navigate(Routes.LOANS) {
                        popUpTo(Routes.LOANS_GRAPH) { inclusive = false }
                    }
                },
            ) {
                Box(
                    modifier = Modifier.size(36.dp).clip(CircleShape).background(LendlyColors.Background.Neutral),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(Icons.Filled.Close, stringResource(R.string.cd_close), tint = LendlyColors.Content.Primary, modifier = Modifier.size(20.dp))
                }
            }
            Row {
                IconButton(onClick = {}) { Icon(Icons.Filled.Info, null, tint = LendlyColors.Content.Secondary) }
                IconButton(onClick = {}) { Icon(Icons.Filled.MoreVert, null, tint = LendlyColors.Content.Secondary) }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = LendlySpacing.Spacing3)
                .padding(bottom = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(72.dp))

            Box(
                modifier = Modifier.size(72.dp).clip(CircleShape).background(LendlyColors.Interactive.Accent),
                contentAlignment = Alignment.Center,
            ) {
                Icon(Icons.Filled.Add, stringResource(R.string.cd_add), tint = LendlyColors.Content.Primary, modifier = Modifier.size(36.dp))
            }

            Spacer(Modifier.height(LendlySpacing.Spacing2))

            Text(
                stringResource(R.string.success_added_to_account),
                style = MaterialTheme.typography.bodyMedium,
                color = LendlyColors.Content.Tertiary,
            )

            Spacer(Modifier.height(LendlySpacing.Spacing1))

            Text(
                "%.2f PHP".format(result.amount),
                fontFamily = Montserrat,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                color = LendlyColors.Content.Primary,
            )

            Spacer(Modifier.height(4.dp))

            Text(
                "From ${result.lender ?: "—"}",
                style = MaterialTheme.typography.bodySmall,
                color = LendlyColors.Content.Tertiary,
            )

            Spacer(Modifier.height(LendlySpacing.Spacing2))

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(100.dp))
                    .background(LendlyColors.Background.Neutral)
                    .padding(horizontal = LendlySpacing.Spacing2, vertical = 6.dp),
            ) {
                Text("Loan Amount", style = MaterialTheme.typography.labelMedium, color = LendlyColors.Interactive.Primary)
            }

            Spacer(Modifier.height(LendlySpacing.Spacing4))

            TransactionDetailsCard(result = result)

            Spacer(Modifier.height(LendlySpacing.Spacing4))

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(stringResource(R.string.success_need_help), style = MaterialTheme.typography.bodySmall, color = LendlyColors.Content.Tertiary)
                Text(stringResource(R.string.success_help_center), style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.SemiBold, color = LendlyColors.Content.Link)
            }
        }

        PrimaryButton(
            text = stringResource(R.string.success_done),
            onClick = {
                viewModel.resetForm()
                navController.navigate(Routes.LOANS) {
                    popUpTo(Routes.LOANS_GRAPH) { inclusive = false }
                }
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = LendlySpacing.Spacing3)
                .padding(bottom = LendlySpacing.Spacing3),
        )
    }
}

@Composable
private fun TransactionDetailsCard(result: LoanApplication) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = LendlyColors.Background.Screen),
        border = androidx.compose.foundation.BorderStroke(1.dp, LendlyColors.Border.Neutral),
        elevation = CardDefaults.cardElevation(0.dp),
    ) {
        Column(Modifier.padding(LendlySpacing.Spacing3), verticalArrangement = Arrangement.spacedBy(LendlySpacing.Spacing2)) {
            Text("Transaction Details", style = MaterialTheme.typography.titleSmall, color = LendlyColors.Content.Primary)
            HorizontalDivider(color = LendlyColors.Border.Neutral)
            TransactionRow("Monthly Fee",       "₱%.2f".format(result.monthlyFee))
            TransactionRow("Interest",          "%.2f%%".format(result.interest))
            TransactionRow("Installment plan",  "${result.installments} Months")
            TransactionRow("Date & Time",       result.date)
            TransactionRow("Transaction Number", result.transactionNumber, valueColor = LendlyColors.Content.Link)
        }
    }
}

@Composable
private fun TransactionRow(label: String, value: String, valueColor: Color = LendlyColors.Content.Primary) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Top) {
        Text(label, style = MaterialTheme.typography.bodySmall, color = LendlyColors.Content.Secondary, modifier = Modifier.weight(1f))
        Spacer(Modifier.width(LendlySpacing.Spacing2))
        Text(value, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Medium, color = valueColor, textAlign = TextAlign.End)
    }
}
