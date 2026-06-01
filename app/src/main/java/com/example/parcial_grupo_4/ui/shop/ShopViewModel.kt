package com.example.parcial_grupo_4.ui.shop

import retrofit2.HttpException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
                _error.value = mapExceptionToUserMessage(e)
            } finally {
                _isLoading.value = false
            }
        }
    }


    private fun mapExceptionToUserMessage(e: Exception): String {
        return when (e) {

            is SocketTimeoutException ->
                "La conexión está tardando demasiado. Por favor, intenta más tarde."

            is UnknownHostException, is IOException ->
                "No hay conexión a internet. Revisa tu red e intenta de nuevo."

            is HttpException -> {
                when (e.code()) {
                    in 400..499 -> "Hubo un problema con la solicitud. Por favor, intenta nuevamente."
                    in 500..599 -> "El servidor está experimentando problemas en este momento."
                    else -> "Ocurrió un error inesperado al conectar con el servidor."
                }
            }

            else ->
                "Ocurrió un error inesperado. Intenta nuevamente más tarde."
        }
    }
}
