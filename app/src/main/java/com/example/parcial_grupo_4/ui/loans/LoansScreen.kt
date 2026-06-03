package com.example.parcial_grupo_4.ui.loans

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import com.example.parcial_grupo_4.R
import com.example.parcial_grupo_4.ui.common.PrimaryButton
import com.example.parcial_grupo_4.ui.navigation.Routes
import com.example.parcial_grupo_4.ui.theme.LendlyColors
import com.example.parcial_grupo_4.ui.theme.LendlySpacing
import com.example.parcial_grupo_4.ui.theme.Montserrat

@Composable
fun LoansScreen(
    viewModel: LoansViewModel,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    // Observamos el estado para triggear el load inicial y exponer el ViewModel
    val loansState by viewModel.loansState.observeAsState(LoansUiState.Idle)

    Box(modifier = modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 80.dp),
            ) {
                item { PromoBanner() }
                item { Spacer(Modifier.height(LendlySpacing.Spacing3)) }
                item {
                    BorrowAmountSection(
                        modifier = Modifier.padding(horizontal = LendlySpacing.Spacing3),
                    )
                }
                item { Spacer(Modifier.height(LendlySpacing.Spacing3)) }
                item {
                    LoanDetailsCard(
                        modifier = Modifier.padding(horizontal = LendlySpacing.Spacing3),
                        onWhatIsThis = { navController.navigate(Routes.ACTIVE_LOANS) },
                    )
                }
                item { Spacer(Modifier.height(LendlySpacing.Spacing4)) }
                item {
                    HowItWorksSection(
                        modifier = Modifier.padding(horizontal = LendlySpacing.Spacing3),
                    )
                }
                item {
                    Text(
                        text = "Ver Active Loans →",
                        style = MaterialTheme.typography.labelLarge,
                        color = LendlyColors.Content.Link,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { navController.navigate(Routes.ACTIVE_LOANS) }
                            .padding(horizontal = LendlySpacing.Spacing3, vertical = LendlySpacing.Spacing2),
                    )
                }
                item { Spacer(Modifier.height(LendlySpacing.Spacing3)) }
            }

            PrimaryButton(
                text = "Get This Loan",
                onClick = { navController.navigate(Routes.LOAN_FORM) },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = LendlySpacing.Spacing3)
                    .padding(bottom = LendlySpacing.Spacing3),
            )
    }
}

// ─── Promo Banner ─────────────────────────────────────────────────────────────

@Composable
private fun PromoBanner() {
    Image(
        painter = painterResource(R.drawable.banner_woman),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(16.dp)),
        contentScale = ContentScale.Crop,
    )
}

// ─── Borrow Amount Section ────────────────────────────────────────────────────

@Composable
private fun BorrowAmountSection(modifier: Modifier = Modifier) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(
            "You can borrow up to",
            style = MaterialTheme.typography.bodyMedium,
            color = LendlyColors.Content.Secondary,
        )
        Text(
            "₱30,000.00",
            fontFamily = Montserrat,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            color = LendlyColors.Content.Primary,
        )
        Text(
            "*Subject to evaluation",
            style = MaterialTheme.typography.labelSmall,
            color = LendlyColors.Content.Tertiary,
        )
    }
}

// ─── Loan Details Card ────────────────────────────────────────────────────────

@Composable
private fun LoanDetailsCard(modifier: Modifier = Modifier, onWhatIsThis: () -> Unit) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = LendlyColors.Background.Elevated),
        border = androidx.compose.foundation.BorderStroke(1.dp, LendlyColors.Border.Neutral),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
    ) {
        Column(Modifier.padding(LendlySpacing.Spacing3)) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    "Loan Details",
                    style = MaterialTheme.typography.titleSmall,
                    color = LendlyColors.Content.Primary,
                )
                Text(
                    "What is this?",
                    style = MaterialTheme.typography.labelMedium,
                    color = LendlyColors.Content.Link,
                    modifier = Modifier.clickable { onWhatIsThis() },
                )
            }
            Spacer(Modifier.height(LendlySpacing.Spacing2))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                LoanDetailItem(label = "Payable in",    value = "6 - 12", sub = "months")
                LoanDetailItem(label = "Interest Rate", value = "1.99%",  sub = "ave per mo.")
                LoanDetailItem(label = "Process Fee",   value = "3%",     sub = "as low as")
            }
        }
    }
}

@Composable
private fun LoanDetailItem(label: String, value: String, sub: String) {
    Column {
        Text(label, style = MaterialTheme.typography.labelSmall, color = LendlyColors.Content.Tertiary)
        Text(value, fontFamily = Montserrat, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = LendlyColors.Content.Primary)
        Text(sub, style = MaterialTheme.typography.labelSmall, color = LendlyColors.Content.Tertiary)
    }
}

// ─── How It Works ─────────────────────────────────────────────────────────────

private data class HowItWorksItem(
    val imageRes: Int,
    val title: String,
    val description: String,
)

@Composable
private fun HowItWorksSection(modifier: Modifier = Modifier) {
    val items = listOf(
        HowItWorksItem(R.drawable.how_it_works_credit,   "Keep your credit score high",    "The offered loan amount is based on your credit score"),
        HowItWorksItem(R.drawable.how_it_works_approval, "Get instant approval",           "Everything we need to process is already in the application"),
        HowItWorksItem(R.drawable.how_it_works_payments, "Easy payments option available", "Skip the queue and pay your due on the application"),
        HowItWorksItem(R.drawable.how_it_works_secure,   "Safe and secure",                "Rayland is working with trusted partners to provide this service"),
    )

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(LendlySpacing.Spacing2)) {
        Text("How it works", style = MaterialTheme.typography.titleMedium, color = LendlyColors.Content.Primary)
        Spacer(Modifier.height(4.dp))
        // Fila 1
        Row(
            modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Max),
            horizontalArrangement = Arrangement.spacedBy(LendlySpacing.Spacing2),
        ) {
            HowItWorksCard(items[0], Modifier.weight(1f).fillMaxHeight())
            HowItWorksCard(items[1], Modifier.weight(1f).fillMaxHeight())
        }
        // Fila 2
        Row(
            modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Max),
            horizontalArrangement = Arrangement.spacedBy(LendlySpacing.Spacing2),
        ) {
            HowItWorksCard(items[2], Modifier.weight(1f).fillMaxHeight())
            HowItWorksCard(items[3], Modifier.weight(1f).fillMaxHeight())
        }
    }
}

@Composable
private fun HowItWorksCard(item: HowItWorksItem, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = LendlyColors.Background.Neutral),
        elevation = CardDefaults.cardElevation(0.dp),
    ) {
        Column(Modifier.padding(LendlySpacing.Spacing2), verticalArrangement = Arrangement.spacedBy(LendlySpacing.Spacing1)) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentScale = ContentScale.Fit,
            )
            Text(item.title, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.SemiBold, color = LendlyColors.Content.Primary)
            Text(item.description, style = MaterialTheme.typography.labelSmall, color = LendlyColors.Content.Tertiary, lineHeight = 16.sp)
        }
    }
}
