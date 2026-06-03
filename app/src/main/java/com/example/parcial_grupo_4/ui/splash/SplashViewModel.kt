package com.example.parcial_grupo_4.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parcial_grupo_4.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    // null = aún chequeando; true/false = resultado de la sesión persistida
    private val _isLoggedIn = MutableLiveData<Boolean?>(null)
    val isLoggedIn: LiveData<Boolean?> = _isLoggedIn

    init {
        viewModelScope.launch {
            _isLoggedIn.value = repository.hasSession()
        }
    }
}
