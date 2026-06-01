package com.example.parcial_grupo_4.ui.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OnboardingViewModel : ViewModel() {
    private val _currentPage = MutableLiveData(0)
    val currentPage: LiveData<Int> = _currentPage

    fun setPage(page: Int) {
        _currentPage.value = page
    }

}