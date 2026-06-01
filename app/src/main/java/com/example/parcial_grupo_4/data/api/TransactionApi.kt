package com.example.parcial_grupo_4.data.api

import com.example.parcial_grupo_4.data.api.dto.TransactionsResponseDto
import retrofit2.http.GET

interface TransactionApi {
    @GET("transactions")
    suspend fun getTransactions(): TransactionsResponseDto
}
