package com.example.parcial_grupo_4.data.api

import com.example.parcial_grupo_4.data.api.dto.LoginRequestDto
import com.example.parcial_grupo_4.data.api.dto.LoginResponseDto
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApi {
    @Headers("x-api-key: 123456789")
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequestDto): LoginResponseDto
}

