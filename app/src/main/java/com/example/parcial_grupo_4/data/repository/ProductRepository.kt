package com.example.parcial_grupo_4.data.repository

import com.example.parcial_grupo_4.data.api.ShopService
import com.example.parcial_grupo_4.data.model.ShopResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(
    private val shopService: ShopService
) {
    suspend fun getProducts(): ShopResponse {
        return shopService.getProducts()
    }
}
