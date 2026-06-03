package com.example.parcial_grupo_4.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Product(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "brand") val brand: String,
    @Json(name = "category") val category: String,
    @Json(name = "price") val price: Double,
    @Json(name = "currency") val currency: String,
    @Json(name = "image") val image: String,
    @Json(name = "monthlyInstallment") val monthlyInstallment: Double,
    @Json(name = "installmentMonths") val installmentMonths: Int,
    @Json(name = "interestRate") val interestRate: Double,
    @Json(name = "isFeatured") val isFeatured: Boolean,
    @Json(name = "isAvailable") val isAvailable: Boolean,
    @Json(name = "rating") val rating: Double,
    @Json(name = "reviewCount") val reviewCount: Int,
    @Json(name = "description") val description: String? = null
)
