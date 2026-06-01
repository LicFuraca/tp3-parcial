package com.example.parcial_grupo_4.data.repository

import com.example.parcial_grupo_4.data.api.AuthApi
import com.example.parcial_grupo_4.data.api.safeApiCall
import com.example.parcial_grupo_4.data.api.dto.LoginRequestDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val api: AuthApi
) {
    suspend fun login(phone: String, password: String) = safeApiCall {
        api.login(LoginRequestDto(phone, password))
    }
}

