package com.example.parcial_grupo_4.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parcial_grupo_4.data.api.NetworkResult
import com.example.parcial_grupo_4.data.repository.AuthRepository
import com.example.parcial_grupo_4.util.toMessageRes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class RegisterState {
    object Idle : RegisterState()
    object Loading : RegisterState()
    object Success : RegisterState()
    data class Error(val messageRes: Int) : RegisterState()
}

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    // Datos que se van juntando a lo largo del flujo de registro
    private var phone: String = ""

    private val _registerState = MutableLiveData<RegisterState>(RegisterState.Idle)
    val registerState: LiveData<RegisterState> = _registerState

    fun setPhone(value: String) {
        phone = value
    }

    fun register(password: String) {
        viewModelScope.launch {
            _registerState.value = RegisterState.Loading
            when (val result = repository.register(phone, password)) {
                is NetworkResult.Success -> _registerState.value = RegisterState.Success
                is NetworkResult.Error -> _registerState.value = RegisterState.Error(result.type.toMessageRes())
            }
        }
    }
}
