package com.example.parcial_grupo_4.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parcial_grupo_4.data.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface TransactionDetailState {
    data object Loading : TransactionDetailState
    data class Found(val transaction: TransactionUiModel) : TransactionDetailState
    data object NotFound : TransactionDetailState
}

@HiltViewModel
class TransactionDetailViewModel @Inject constructor(
    repository: TransactionRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = MutableLiveData<TransactionDetailState>(TransactionDetailState.Loading)
    val state: LiveData<TransactionDetailState> = _state

    init {
        val id = savedStateHandle.get<String>(ARG_TRANSACTION_ID).orEmpty()
        viewModelScope.launch {
            val transaction = repository.getById(id)
            _state.value = transaction
                ?.let { TransactionDetailState.Found(it) }
                ?: TransactionDetailState.NotFound
        }
    }

    companion object {
        // Debe coincidir con el navArgument de la ruta de detalle en MainScreen.
        const val ARG_TRANSACTION_ID = "transactionId"
    }
}
