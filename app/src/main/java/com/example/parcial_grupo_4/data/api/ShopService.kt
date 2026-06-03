package com.example.parcial_grupo_4.data.api

import com.example.parcial_grupo_4.data.model.ShopResponse
import retrofit2.http.GET

interface ShopService {
    @GET("products")
    suspend fun getProducts(): ShopResponse
}
