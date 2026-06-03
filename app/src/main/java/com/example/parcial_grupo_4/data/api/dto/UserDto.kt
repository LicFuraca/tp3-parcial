package com.example.parcial_grupo_4.data.api.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDto(
    val id: String,
    val firstName: String,
    val lastName: String,
    val birthDate: String,
    val address: String,
    val city: String,
    val postalCode: String,
    val phonePrefix: String,
    val phoneNumber: String,
    val avatarUrl: String? = null // Para usar con Glide/Coil
)