package com.example.parcial_grupo_4.ui.loans

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.parcial_grupo_4.R
import com.example.parcial_grupo_4.data.model.InstallmentPlan
import com.example.parcial_grupo_4.ui.common.PrimaryButton
import com.example.parcial_grupo_4.ui.navigation.Routes
import com.example.parcial_grupo_4.ui.theme.LendlyColors
import com.example.parcial_grupo_4.ui.theme.LendlySpacing
import com.example.parcial_grupo_4.ui.theme.Montserrat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoanFormScreen(
    viewModel: LoansViewModel,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val amountInput     by viewModel.amountInput.observeAsState("")
    val selectedPlan    by viewModel.selectedPlan.observeAsState(null)
    val selectedPurpose by viewModel.selectedPurpose.observeAsState("")
    val applyState      by viewModel.applyState.observeAsState(ApplyUiState.Idle)

    // Fix: limpiar estado al entrar al form para evitar que un Success previo
    // redirija al usuario apenas abre el formulario de nuevo
    LaunchedEffect(Unit) {
        viewModel.resetApplyState()
    }

    LaunchedEffect(applyState) {
        if (applyState is ApplyUiState.Success) {
            navController.navigate(Routes.LOAN_SUCCESS) {
                popUpTo(Routes.LOAN_FORM) { inclusive = true }
            }
        }
    }

    val amount        = amountInput.toDoubleOrNull() ?: 0.0
    val processingFee = amount * 0.03

    // Estado de validación — solo se muestra al intentar enviar
    var showErrors by remember { mutableStateOf(false) }
    val amountError   = when {
        !showErrors                              -> null
        amountInput.isBlank()                   -> stringResource(R.string.error_amount_required)
        amount <= 0                             -> stringResource(R.string.error_amount_invalid)
        else                                    -> null
    }
    val planError    = if (showErrors && selectedPlan == null) stringResource(R.string.error_plan_required) else null
    val purposeError = if (showErrors && selectedPurpose.isBlank()) stringResource(R.string.error_purpose_required) else null
    val isFormValid  = amount > 0 && selectedPlan != null && selectedPurpose.isNotBlank()

    Column(modifier = modifier.fillMaxSize()) {
        TopAppBar(
            windowInsets = WindowInsets(0),
            colors = TopAppBarDefaults.topAppBarColors(containerColor = LendlyColors.Background.Screen),
            title = {
                Text(
                    stringResource(R.string.loan_form_title),
                    style = MaterialTheme.typography.titleMedium,
                    color = LendlyColors.Content.Primary,
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.cd_back),
                        tint = LendlyColors.Content.Primary,
                    )
                }
            },
            actions = {
                IconButton(onClick = {}) {
                    Icon(
                        Icons.Filled.Info,
                        contentDescription = stringResource(R.string.cd_info),
                        tint = LendlyColors.Content.Primary,
                    )
                }
            },
        )

        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = LendlySpacing.Spacing3)
                    .padding(bottom = 80.dp),
                verticalArrangement = Arrangement.spacedBy(LendlySpacing.Spacing4),
            ) {
                Spacer(Modifier.height(LendlySpacing.Spacing2))

                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        stringResource(R.string.loan_form_header),
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineSmall,
                        color = LendlyColors.Content.Primary,
                    )
                    Text(
                        stringResource(R.string.loan_form_subtitle),
                        style = MaterialTheme.typography.bodySmall,
                        color = LendlyColors.Content.Tertiary,
                    )
                }

                FormStep(stringResource(R.string.loan_form_step1), stringResource(R.string.loan_form_step1_label)) {
                    AmountInput(
                        value = amountInput,
                        onValueChange = { viewModel.setAmount(it) },
                        errorMessage = amountError,
                    )
                }

                FormStep(stringResource(R.string.loan_form_step2), stringResource(R.string.loan_form_step2_label)) {
                    InstallmentPlansColumn(
                        plans = viewModel.availablePlans,
                        selectedPlan = selectedPlan,
                        principal = amount,
                        onSelect = { viewModel.selectPlan(it) },
                        errorMessage = planError,
                    )
                }

                FormStep(stringResource(R.string.loan_form_step3), stringResource(R.string.loan_form_step3_label)) {
                    PurposeDropdown(
                        options = viewModel.loanPurposes,
                        selected = selectedPurpose,
                        onSelect = { viewModel.selectPurpose(it) },
                        errorMessage = purposeError,
                    )
                }

                LoanSummaryCard(amount = amount, processingFee = processingFee)

                Spacer(Modifier.height(LendlySpacing.Spacing2))
            }

            if (applyState is ApplyUiState.Error) {
                Snackbar(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(LendlySpacing.Spacing3)
                        .padding(bottom = 80.dp),
                    containerColor = LendlyColors.Sentiment.Negative,
                ) {
                    Text((applyState as ApplyUiState.Error).message, color = LendlyColors.Contrast.Light)
                }
            }

            PrimaryButton(
                text = if (applyState is ApplyUiState.Loading)
                    stringResource(R.string.loan_form_processing)
                else
                    stringResource(R.string.loans_get_this_loan),
                onClick = {
                    showErrors = true
                    if (isFormValid) viewModel.applyLoan()
                },
                enabled = applyState !is ApplyUiState.Loading,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = LendlySpacing.Spacing3)
                    .padding(bottom = LendlySpacing.Spacing3),
            )
        }
    }
}

// ─── Form Step Wrapper ────────────────────────────────────────────────────────

@Composable
private fun FormStep(stepNumber: String, label: String, content: @Composable () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(LendlySpacing.Spacing1)) {
        Text(stepNumber, style = MaterialTheme.typography.labelSmall, color = LendlyColors.Content.Tertiary)
        Text(label, style = MaterialTheme.typography.titleSmall, color = LendlyColors.Content.Primary)
        Spacer(Modifier.height(4.dp))
        content()
    }
}

// ─── Amount Input ─────────────────────────────────────────────────────────────

@Composable
private fun AmountInput(
    value: String,
    onValueChange: (String) -> Unit,
    errorMessage: String? = null,
) {
    Column {
        OutlinedTextField(
            value = value,
            onValueChange = { new ->
                // Fix: solo un punto decimal permitido
                val dotCount = new.count { it == '.' }
                if (new.all { it.isDigit() || it == '.' } && dotCount <= 1) onValueChange(new)
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("0.00", color = LendlyColors.Content.Tertiary) },
            prefix = {
                Text(
                    "₱",
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineSmall,
                    color = LendlyColors.Content.Primary,
                )
            },
            textStyle = MaterialTheme.typography.headlineSmall.copy(
                fontFamily = Montserrat,
                fontWeight = FontWeight.Bold,
                color = LendlyColors.Content.Primary,
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            singleLine = true,
            isError = errorMessage != null,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor   = LendlyColors.Interactive.Primary,
                unfocusedBorderColor = LendlyColors.Border.Neutral,
                errorBorderColor     = LendlyColors.Sentiment.Negative,
                cursorColor          = LendlyColors.Interactive.Primary,
            ),
            shape = RoundedCornerShape(12.dp),
        )
        if (errorMessage != null) {
            Text(
                errorMessage,
                style = MaterialTheme.typography.labelSmall,
                color = LendlyColors.Sentiment.Negative,
                modifier = Modifier.padding(start = 4.dp, top = 4.dp),
            )
        }
    }
}

// ─── Installment Plans ────────────────────────────────────────────────────────

@Composable
private fun InstallmentPlansColumn(
    plans: List<InstallmentPlan>,
    selectedPlan: InstallmentPlan?,
    principal: Double,
    onSelect: (InstallmentPlan) -> Unit,
    errorMessage: String? = null,
) {
    Column(verticalArrangement = Arrangement.spacedBy(LendlySpacing.Spacing1)) {
        plans.forEach { plan ->
            val isSelected = plan == selectedPlan
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .border(
                        width = if (isSelected) 2.dp else 1.dp,
                        color = when {
                            isSelected   -> LendlyColors.Interactive.Primary
                            errorMessage != null -> LendlyColors.Sentiment.Negative
                            else         -> LendlyColors.Border.Neutral
                        },
                        shape = RoundedCornerShape(12.dp),
                    )
                    .background(if (isSelected) LendlyColors.Background.Neutral else LendlyColors.Background.Screen)
                    .clickable { onSelect(plan) }
                    .padding(horizontal = LendlySpacing.Spacing3, vertical = LendlySpacing.Spacing2),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column {
                    Text("${plan.months} ${stringResource(R.string.loan_form_months)}", style = MaterialTheme.typography.titleSmall, color = LendlyColors.Content.Primary)
                    Text("${plan.interestPercent}% Interest", style = MaterialTheme.typography.labelSmall, color = LendlyColors.Content.Tertiary)
                }
                if (principal > 0) {
                    Text(
                        "₱%.2f/mo".format(plan.monthlyPayment(principal)),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = LendlyColors.Content.Primary,
                    )
                }
            }
        }
        if (errorMessage != null) {
            Text(
                errorMessage,
                style = MaterialTheme.typography.labelSmall,
                color = LendlyColors.Sentiment.Negative,
                modifier = Modifier.padding(start = 4.dp, top = 4.dp),
            )
        }
    }
}

// ─── Purpose Dropdown ─────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PurposeDropdown(
    options: List<String>,
    selected: String,
    onSelect: (String) -> Unit,
    errorMessage: String? = null,
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = it }) {
            OutlinedTextField(
                value = selected.ifBlank { stringResource(R.string.loan_form_select_purpose) },
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.fillMaxWidth().menuAnchor(),
                trailingIcon = {
                    Icon(Icons.Filled.KeyboardArrowDown, null, tint = LendlyColors.Content.Secondary)
                },
                isError = errorMessage != null,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor   = LendlyColors.Interactive.Primary,
                    unfocusedBorderColor = LendlyColors.Border.Neutral,
                    errorBorderColor     = LendlyColors.Sentiment.Negative,
                    unfocusedTextColor   = if (selected.isBlank()) LendlyColors.Content.Tertiary else LendlyColors.Content.Primary,
                ),
                shape = RoundedCornerShape(12.dp),
            )
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option, style = MaterialTheme.typography.bodyMedium) },
                        onClick = { onSelect(option); expanded = false },
                    )
                }
            }
        }
        if (errorMessage != null) {
            Text(
                errorMessage,
                style = MaterialTheme.typography.labelSmall,
                color = LendlyColors.Sentiment.Negative,
                modifier = Modifier.padding(start = 4.dp, top = 4.dp),
            )
        }
    }
}

// ─── Summary Card ─────────────────────────────────────────────────────────────

@Composable
private fun LoanSummaryCard(amount: Double, processingFee: Double) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = LendlyColors.Background.Elevated),
        border = androidx.compose.foundation.BorderStroke(1.dp, LendlyColors.Border.Neutral),
        elevation = CardDefaults.cardElevation(0.dp),
    ) {
        Column(Modifier.padding(LendlySpacing.Spacing3), verticalArrangement = Arrangement.spacedBy(LendlySpacing.Spacing2)) {
            Text(stringResource(R.string.loan_form_summary_title), style = MaterialTheme.typography.titleSmall, color = LendlyColors.Content.Primary)

            SummaryRow(
                stringResource(R.string.loan_form_loan_amount),
                if (amount > 0) "PHP %.2f".format(amount) else "PHP 0.00",
            )
            SummaryRow(
                stringResource(R.string.loan_form_processing_fee),
                if (amount > 0) "-%.2f".format(processingFee) else "0.00",
                valueColor = LendlyColors.Sentiment.Negative,
            )

            HorizontalDivider(color = LendlyColors.Border.Neutral)

            SummaryRow(
                stringResource(R.string.loan_form_total),
                if (amount > 0) "₱%.2f".format(amount) else "₱0.00",
                isBold = true,
            )
            SummaryRow(stringResource(R.string.loan_form_lender), "—", valueColor = LendlyColors.Content.Tertiary)

            Text(
                stringResource(R.string.loans_what_is_this),
                style = MaterialTheme.typography.labelMedium,
                color = LendlyColors.Content.Link,
                modifier = Modifier.clickable { },
            )
        }
    }
}

@Composable
private fun SummaryRow(
    label: String,
    value: String,
    valueColor: androidx.compose.ui.graphics.Color = LendlyColors.Content.Primary,
    isBold: Boolean = false,
) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, style = MaterialTheme.typography.bodySmall, color = LendlyColors.Content.Secondary)
        Text(
            value,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = if (isBold) FontWeight.SemiBold else FontWeight.Normal,
            color = valueColor,
        )
    }
}
