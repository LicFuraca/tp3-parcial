package com.example.parcial_grupo_4.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.parcial_grupo_4.data.api.NetworkResult
import com.example.parcial_grupo_4.data.repository.TransactionRepository
import com.example.parcial_grupo_4.util.toMessageRes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TransactionSection(
    val title: String,
    val items: List<TransactionUiModel>,
)

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val repository: TransactionRepository,
) : ViewModel() {

    // Fuente de verdad: Room. Se agrupan las transacciones por mes preservando el
    // orden por fecha (el DAO ya las devuelve de más reciente a más antigua).
    val sections: LiveData<List<TransactionSection>> =
        repository.observeTransactions().map { transactions ->
            transactions.groupBy { it.monthLabel }
                .map { (month, items) -> TransactionSection(month, items) }
        }

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessageRes = MutableLiveData<Int?>(null)
    val errorMessageRes: LiveData<Int?> = _errorMessageRes

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessageRes.value = null
            when (val result = repository.refresh()) {
                is NetworkResult.Error -> _errorMessageRes.value = result.type.toMessageRes()
                is NetworkResult.Success -> Unit
            }
            _isLoading.value = false
        }
    }
}
