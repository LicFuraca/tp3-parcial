package com.example.parcial_grupo_4.data.repository

import com.example.parcial_grupo_4.data.api.LoanApiService
import java.text.SimpleDateFormat
import java.util.Locale
import com.example.parcial_grupo_4.data.api.dto.LoanApplyRequestDto
import com.example.parcial_grupo_4.data.api.dto.LoanItemDto
import com.example.parcial_grupo_4.data.model.LoanApplication
import com.example.parcial_grupo_4.data.model.LoanItem
import com.example.parcial_grupo_4.data.model.LoanStatus
import com.example.parcial_grupo_4.data.model.LoansData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoanRepository @Inject constructor(
    private val api: LoanApiService,
) {
    suspend fun getLoans(): LoansData {
        val dto = api.getLoans()
        val allLoans = dto.loans?.map { it.toDomain() } ?: emptyList()
        return LoansData(
            active  = allLoans.filter { it.status == LoanStatus.ACTIVE || it.status == LoanStatus.PENDING },
            history = allLoans.filter { it.status == LoanStatus.PAID },
        )
    }

    suspend fun applyLoan(amount: Double, installments: Int, purpose: String): LoanApplication {
        val response = api.applyLoan(LoanApplyRequestDto(amount, installments, purpose))
        val loan = response.loan
        return LoanApplication(
            transactionNumber = loan?.id ?: "#${System.currentTimeMillis()}",
            amount            = loan?.amount ?: amount,
            lender            = "Rayland",
            monthlyFee        = loan?.installmentAmount ?: 0.0,
            interest          = loan?.interestRate ?: 0.0,
            installments      = installments,
            date              = formatDate(loan?.startDate),
            processingFee     = loan?.processingFee ?: (amount * 0.03),
        )
    }

    private fun formatDate(isoDate: String?): String {
        if (isoDate.isNullOrBlank()) return ""
        return try {
            val input  = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            val output = SimpleDateFormat("MMM dd, yyyy h:mm a", Locale.US)
            output.format(input.parse(isoDate)!!)
        } catch (e: Exception) {
            isoDate
        }
    }

    private fun LoanItemDto.toDomain() = LoanItem(
        id          = id ?: "",
        name        = installmentPlan ?: "",
        lender      = lender ?: "",
        amount      = amount ?: 0.0,
        monthlyFee  = installmentAmount ?: 0.0,
        interest    = interestRate ?: 0.0,
        installments = totalInstallments ?: 0,
        status      = when (status?.uppercase()) {
            "ACTIVE"  -> LoanStatus.ACTIVE
            "PAID"    -> LoanStatus.PAID
            else      -> LoanStatus.PENDING
        },
        date        = startDate ?: "",
        fees        = nextPaymentLabel,
        imageUrl    = lenderLogo?.let { url ->
            // Clearbit está bloqueado en Android; usamos Google Favicons
            val domain = url.substringAfterLast("/")
            "https://www.google.com/s2/favicons?domain=$domain&sz=128"
        },
    )
}
