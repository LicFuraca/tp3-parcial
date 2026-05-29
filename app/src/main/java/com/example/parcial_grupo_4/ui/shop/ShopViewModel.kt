package com.example.parcial_grupo_4.ui.shop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parcial_grupo_4.data.model.ShopResponse
import com.example.parcial_grupo_4.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _shopData = MutableLiveData<ShopResponse>()
    val shopData: LiveData<ShopResponse> = _shopData

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
                _shopData.value = response
            } catch (e: Exception) {
                _error.value = "Error al cargar los productos: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
