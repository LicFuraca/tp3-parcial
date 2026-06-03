package com.example.parcial_grupo_4.data.api.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateRequestDto(
    val phone: String,
    val password: String
)
