package com.example.parcial_grupo_4.data.api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// ── GET /loans ───────────────────────────────────────────────────────────────

@JsonClass(generateAdapter = true)
data class LoansResponseDto(
    @Json(name = "success") val success: Boolean? = null,
    @Json(name = "loans")   val loans: List<LoanItemDto>? = null,
)

@JsonClass(generateAdapter = true)
data class LoanItemDto(
    @Json(name = "id")               val id: String? = null,
    @Json(name = "lender")           val lender: String? = null,
    @Json(name = "lenderLogo")       val lenderLogo: String? = null,
    @Json(name = "amount")           val amount: Double? = null,
    @Json(name = "amountDue")        val amountDue: Double? = null,
    @Json(name = "installmentAmount") val installmentAmount: Double? = null,
    @Json(name = "installmentPlan")  val installmentPlan: String? = null,
    @Json(name = "interestRate")     val interestRate: Double? = null,
    @Json(name = "purpose")          val purpose: String? = null,
    @Json(name = "status")           val status: String? = null,
    @Json(name = "nextPaymentDate")  val nextPaymentDate: String? = null,
    @Json(name = "nextPaymentLabel") val nextPaymentLabel: String? = null,
    @Json(name = "startDate")        val startDate: String? = null,
    @Json(name = "endDate")          val endDate: String? = null,
    @Json(name = "paidInstallments") val paidInstallments: Int? = null,
    @Json(name = "totalInstallments") val totalInstallments: Int? = null,
)

// ── POST /loans/apply ─────────────────────────────────────────────────────────

@JsonClass(generateAdapter = true)
data class LoanApplyRequestDto(
    @Json(name = "amount")       val amount: Double,
    @Json(name = "installments") val installments: Int,
    @Json(name = "purpose")      val purpose: String,
)

@JsonClass(generateAdapter = true)
data class LoanApplyResponseDto(
    @Json(name = "success") val success: Boolean? = null,
    @Json(name = "message") val message: String? = null,
    @Json(name = "loan")    val loan: LoanApplyDataDto? = null,
)

@JsonClass(generateAdapter = true)
data class LoanApplyDataDto(
    @Json(name = "id")              val id: String? = null,
    @Json(name = "amount")          val amount: Double? = null,
    @Json(name = "processingFee")   val processingFee: Double? = null,
    @Json(name = "netAmount")       val netAmount: Double? = null,
    @Json(name = "installmentAmount") val installmentAmount: Double? = null,
    @Json(name = "installmentPlan") val installmentPlan: String? = null,
    @Json(name = "interestRate")    val interestRate: Double? = null,
    @Json(name = "purpose")         val purpose: String? = null,
    @Json(name = "status")          val status: String? = null,
    @Json(name = "startDate")       val startDate: String? = null,
    @Json(name = "endDate")         val endDate: String? = null,
    @Json(name = "nextPaymentDate") val nextPaymentDate: String? = null,
)
