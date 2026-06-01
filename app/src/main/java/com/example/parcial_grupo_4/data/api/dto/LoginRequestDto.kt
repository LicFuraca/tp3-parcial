package com.example.parcial_grupo_4.data.api.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginRequestDto(
    val phone: String,
    val password: String
)

