package com.example.parcial_grupo_4.data.model

data class LoanItem(
    val id: String,
    val name: String,
    val lender: String,
    val amount: Double,
    val monthlyFee: Double,
    val interest: Double,
    val installments: Int,
    val status: LoanStatus,
    val date: String,
    val fees: String?,
    val imageUrl: String?,
)

enum class LoanStatus { ACTIVE, PAID, PENDING }

data class LoansData(
    val active: List<LoanItem>,
    val history: List<LoanItem>,
)

data class LoanApplication(
    val transactionNumber: String,
    val amount: Double,
    val lender: String?,
    val monthlyFee: Double,
    val interest: Double,
    val installments: Int,
    val date: String,
    val processingFee: Double,
)

data class InstallmentPlan(
    val months: Int,
    val interestPercent: Double,
) {
    fun monthlyPayment(principal: Double): Double =
        if (principal <= 0.0) 0.0
        else (principal * (1.0 + interestPercent / 100.0)) / months.toDouble()
}
