package com.example.parcial_grupo_4.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parcial_grupo_4.data.model.LoanItem
import com.example.parcial_grupo_4.data.model.Product
import com.example.parcial_grupo_4.data.repository.LoanRepository
import com.example.parcial_grupo_4.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val isLoading: Boolean = false,
    val recommendedProducts: List<Product> = emptyList(),
    val unpaidLoans: List<LoanItem> = emptyList(),
    val error: String? = null,
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val loanRepository: LoanRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadHomeData()
    }

    fun loadHomeData() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                error = null,
            )

            try {
                val productsResponse = productRepository.getProducts()
                val loansData = loanRepository.getLoans()

                _uiState.value = HomeUiState(
                    isLoading = false,
                    recommendedProducts = productsResponse.products.take(5),
                    unpaidLoans = loansData.active.take(2),
                    error = null,
                )
            } catch (e: Exception) {
                _uiState.value = HomeUiState(
                    isLoading = false,
                    error = "No pudimos cargar la información del Home.",
                )
            }
        }
    }
}