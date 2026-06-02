package com.example.parcial_grupo_4.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parcial_grupo_4.data.api.NetworkResult
import com.example.parcial_grupo_4.data.repository.AuthRepository
import com.example.parcial_grupo_4.util.toMessageRes // Importa la extensión
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    object Success : LoginState()
    data class Error(val messageRes: Int) : LoginState()
}

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun login(phone: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading

            val result = repository.login(phone, password)

            when (result) {
                is NetworkResult.Success -> {
                    _loginState.value = LoginState.Success
                }
                is NetworkResult.Error -> {
                    val messageRes = result.type.toMessageRes()
                    _loginState.value = LoginState.Error(messageRes)
                }
            }
        }
    }
}