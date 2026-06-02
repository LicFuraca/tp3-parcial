package com.example.parcial_grupo_4.data.repository

import com.example.parcial_grupo_4.data.api.AuthApi
import com.example.parcial_grupo_4.data.api.NetworkResult
import com.example.parcial_grupo_4.data.api.dto.LoginRequestDto
import com.example.parcial_grupo_4.data.local.AuthDao
import com.example.parcial_grupo_4.data.local.UserEntity
import com.example.parcial_grupo_4.data.api.safeApiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val api: AuthApi,
    private val authDao: AuthDao
) {
    suspend fun login(phone: String, password: String): NetworkResult<String> {
        val result = safeApiCall { api.login(LoginRequestDto(phone, password)) }

        // Si la llamada fue exitosa, guardamos en Room
        if (result is NetworkResult.Success) {
            val token = result.data.token // Asumiendo que el DTO tiene un campo 'token'
            authDao.saveToken(UserEntity(token = token))
            return NetworkResult.Success(token)
        }

        // Si falló, devolvemos el error
        return result as NetworkResult.Error
    }
}

