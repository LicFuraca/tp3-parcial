package com.example.parcial_grupo_4.data.api.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TransactionsResponseDto(
    val success: Boolean,
    val pagination: PaginationDto? = null,
    val transactions: List<TransactionDto> = emptyList(),
)

@JsonClass(generateAdapter = true)
data class PaginationDto(
    val page: Int,
    val limit: Int,
    val total: Int,
    val hasNextPage: Boolean,
)

@JsonClass(generateAdapter = true)
data class TransactionDto(
    val id: String,
    val type: String,
    val title: String,
    val description: String,
    val amount: Double,
    val currency: String,
    val status: String,
    val date: String,
    val loanId: String? = null,
    val referenceNumber: String,
)
