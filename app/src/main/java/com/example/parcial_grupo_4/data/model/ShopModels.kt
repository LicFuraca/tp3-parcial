package com.example.parcial_grupo_4.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShopResponse(
    @Json(name = "success") val success: Boolean,
    @Json(name = "pagination") val pagination: Pagination,
    @Json(name = "featured") val featured: List<Product>,
    @Json(name = "categories") val categories: List<Category>,
    @Json(name = "brands") val brands: List<Brand>,
    @Json(name = "products") val products: List<Product>
)

@JsonClass(generateAdapter = true)
data class Pagination(
    @Json(name = "page") val page: Int,
    @Json(name = "limit") val limit: Int,
    @Json(name = "total") val total: Int,
    @Json(name = "hasNextPage") val hasNextPage: Boolean
)

@JsonClass(generateAdapter = true)
data class Category(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "icon") val icon: String,
    @Json(name = "productCount") val productCount: Int
)

@JsonClass(generateAdapter = true)
data class Brand(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "logo") val logo: String
)
