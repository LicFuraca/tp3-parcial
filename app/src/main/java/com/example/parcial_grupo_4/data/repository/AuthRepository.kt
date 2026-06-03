package com.example.parcial_grupo_4.data.repository

import com.example.parcial_grupo_4.data.api.AuthApi
import com.example.parcial_grupo_4.data.api.NetworkResult
import com.example.parcial_grupo_4.data.api.dto.LoginRequestDto
import com.example.parcial_grupo_4.data.api.safeApiCall
import com.example.parcial_grupo_4.data.local.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val api: AuthApi,
    private val sessionManager: SessionManager
) {
    suspend fun login(phone: String, password: String): NetworkResult<String> {
        val result = safeApiCall { api.login(LoginRequestDto(phone, password)) }

        // Si la llamada fue exitosa, guardamos el token en almacenamiento cifrado
        if (result is NetworkResult.Success) {
            val token = result.data.token
            withContext(Dispatchers.IO) { sessionManager.saveToken(token) }
            return NetworkResult.Success(token)
        }

        // Si falló, devolvemos el error
        return result as NetworkResult.Error
    }

    suspend fun logout() {
        withContext(Dispatchers.IO) { sessionManager.clearToken() }
    }

    suspend fun hasSession(): Boolean =
        withContext(Dispatchers.IO) { !sessionManager.getToken().isNullOrBlank() }
}
