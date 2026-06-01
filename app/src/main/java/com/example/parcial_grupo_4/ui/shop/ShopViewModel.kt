package com.example.parcial_grupo_4.ui.shop

import retrofit2.HttpException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import com.example.parcial_grupo_4.data.model.ShopResponse
import com.example.parcial_grupo_4.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject


@HiltViewModel
class ShopViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _shopData = MutableLiveData<ShopResponse>()
    val shopData: LiveData<ShopResponse> = _shopData

    private var originalShopData: ShopResponse? = null
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    init {
        fetchProducts()
    }


    fun fetchProducts() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = repository.getProducts()
                originalShopData = response
                _shopData.value = response
            } catch (e: Exception) {
                _error.value = mapExceptionToUserMessage(e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun searchProducts(query: String) {
        val currentData = originalShopData ?: return

        if(query.isBlank()){
            _shopData.value = currentData
            return
        }

        var lowerQuery = query.lowercase()

        val filteredProducts = currentData.products.filter {
            it.name.lowercase().contains(lowerQuery)
        }
        val filteredFeatured = currentData.featured.filter {
            it.name.lowercase().contains(lowerQuery)
        }

        _shopData.value = currentData.copy(
            products = filteredProducts,
            featured = filteredFeatured
        )
    }

    private fun mapExceptionToUserMessage(e: Exception): String {
        return when (e) {

            is SocketTimeoutException ->
                "The connection is taking too long. Please try again later."

            is UnknownHostException, is IOException ->
                "No internet connection. Please check your network and try again."

            is HttpException -> {
                when (e.code()) {
                    in 400..499 -> "There was a problem with the request. Please try again."
                    in 500..599 -> "The server is currently experiencing problems. Please try again later."
                    else -> "An unexpected error occurred while connecting to the server."
                }
            }

            else ->
                "An unexpected error occurred. Please try again later."
        }
    }
}
