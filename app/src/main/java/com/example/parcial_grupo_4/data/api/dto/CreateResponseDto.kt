package com.example.parcial_grupo_4.data.api.dto

import com.squareup.moshi.JsonClass

// El mock devuelve también success/message/user; Moshi ignora lo que no mapeamos.
@JsonClass(generateAdapter = true)
data class CreateResponseDto(
    val token: String
)
