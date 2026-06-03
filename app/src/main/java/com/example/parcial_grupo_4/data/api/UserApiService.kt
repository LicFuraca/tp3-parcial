package com.example.parcial_grupo_4.data.api

import com.example.parcial_grupo_4.data.api.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApiService {
    @GET("users/{id}")
    suspend fun getUserProfile(@Path("id") userId: String): UserDto
}