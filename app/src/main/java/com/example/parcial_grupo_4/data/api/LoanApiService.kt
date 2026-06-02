package com.example.parcial_grupo_4.data.api

import com.example.parcial_grupo_4.data.api.dto.LoanApplyRequestDto
import com.example.parcial_grupo_4.data.api.dto.LoanApplyResponseDto
import com.example.parcial_grupo_4.data.api.dto.LoansResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LoanApiService {
    @GET("loans")
    suspend fun getLoans(): LoansResponseDto

    @POST("loans/apply")
    suspend fun applyLoan(@Body request: LoanApplyRequestDto): LoanApplyResponseDto
}
