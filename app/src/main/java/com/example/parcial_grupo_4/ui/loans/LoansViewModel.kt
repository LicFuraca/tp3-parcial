package com.example.parcial_grupo_4.ui.loans

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parcial_grupo_4.data.model.InstallmentPlan
import com.example.parcial_grupo_4.data.model.LoanApplication
import com.example.parcial_grupo_4.data.model.LoansData
import com.example.parcial_grupo_4.data.repository.LoanRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoansViewModel @Inject constructor(
    private val repository: LoanRepository,
) : ViewModel() {

    val availablePlans = listOf(
        InstallmentPlan(months = 6,  interestPercent = 2.99),
        InstallmentPlan(months = 12, interestPercent = 3.99),
        InstallmentPlan(months = 24, interestPercent = 4.99),
    )

    val loanPurposes = listOf("Educational", "Personal", "Business", "Medical", "Emergency")

    // ── Loans list ──────────────────────────────────────────────────────────

    private val _loansState = MutableLiveData<LoansUiState>(LoansUiState.Idle)
    val loansState: LiveData<LoansUiState> = _loansState

    // ── Form state ──────────────────────────────────────────────────────────

    private val _amountInput = MutableLiveData("")
    val amountInput: LiveData<String> = _amountInput

    private val _selectedPlan = MutableLiveData<InstallmentPlan?>()
    val selectedPlan: LiveData<InstallmentPlan?> = _selectedPlan

    private val _selectedPurpose = MutableLiveData("")
    val selectedPurpose: LiveData<String> = _selectedPurpose

    // ── Apply result ────────────────────────────────────────────────────────

    private val _applyState = MutableLiveData<ApplyUiState>(ApplyUiState.Idle)
    val applyState: LiveData<ApplyUiState> = _applyState

    // ── Actions ─────────────────────────────────────────────────────────────

    init { loadLoans() }

    fun loadLoans() {
        _loansState.value = LoansUiState.Loading
        viewModelScope.launch {
            _loansState.value = try {
                LoansUiState.Success(repository.getLoans())
            } catch (e: Exception) {
                LoansUiState.Error(e.message ?: "Error al cargar préstamos")
            }
        }
    }

    fun setAmount(value: String) { _amountInput.value = value }

    fun selectPlan(plan: InstallmentPlan) { _selectedPlan.value = plan }

    fun selectPurpose(purpose: String) { _selectedPurpose.value = purpose }

    fun applyLoan() {
        val amount  = _amountInput.value?.toDoubleOrNull() ?: return
        val plan    = _selectedPlan.value ?: return
        val purpose = _selectedPurpose.value?.takeIf { it.isNotBlank() } ?: return

        _applyState.value = ApplyUiState.Loading
        viewModelScope.launch {
            _applyState.value = try {
                ApplyUiState.Success(repository.applyLoan(amount, plan.months, purpose))
            } catch (e: Exception) {
                ApplyUiState.Error(e.message ?: "Error al solicitar préstamo")
            }
        }
    }

    fun resetApplyState() { _applyState.value = ApplyUiState.Idle }

    fun resetForm() {
        _amountInput.value  = ""
        _selectedPlan.value = null
        _selectedPurpose.value = ""
        _applyState.value   = ApplyUiState.Idle
    }
}

sealed interface LoansUiState {
    data object Idle    : LoansUiState
    data object Loading : LoansUiState
    data class  Success(val data: LoansData)  : LoansUiState
    data class  Error(val message: String)    : LoansUiState
}

sealed interface ApplyUiState {
    data object Idle    : ApplyUiState
    data object Loading : ApplyUiState
    data class  Success(val result: LoanApplication) : ApplyUiState
    data class  Error(val message: String)           : ApplyUiState
}
