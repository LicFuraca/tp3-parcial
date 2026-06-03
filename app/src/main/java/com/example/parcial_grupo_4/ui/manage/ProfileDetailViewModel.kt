package com.example.parcial_grupo_4.ui.manage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parcial_grupo_4.data.api.UserApiService
import com.example.parcial_grupo_4.data.api.dto.UserDto
import com.example.parcial_grupo_4.data.api.safeApiCall
import com.example.parcial_grupo_4.data.api.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileDetailViewModel @Inject constructor(
    private val api: UserApiService
) : ViewModel() {

    // --- ESTADOS DE LA API ---
    private val _userProfile = MutableLiveData<UserDto>()
    val userProfile: LiveData<UserDto> = _userProfile

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    // --- ESTADOS DEL FORMULARIO (Lo que le faltaba a tu vista) ---
    private val _firstName = MutableLiveData("")
    val firstName: LiveData<String> = _firstName

    private val _lastName = MutableLiveData("")
    val lastName: LiveData<String> = _lastName

    private val _birthDay = MutableLiveData("")
    val birthDay: LiveData<String> = _birthDay

    private val _birthMonth = MutableLiveData("")
    val birthMonth: LiveData<String> = _birthMonth

    private val _birthYear = MutableLiveData("")
    val birthYear: LiveData<String> = _birthYear

    private val _address = MutableLiveData("")
    val address: LiveData<String> = _address

    private val _city = MutableLiveData("")
    val city: LiveData<String> = _city

    private val _postalCode = MutableLiveData("")
    val postalCode: LiveData<String> = _postalCode

    private val _phonePrefix = MutableLiveData("+65") // Valor por defecto según tu vista
    val phonePrefix: LiveData<String> = _phonePrefix

    private val _phoneNumber = MutableLiveData("")
    val phoneNumber: LiveData<String> = _phoneNumber


    // --- FUNCIONES PARA ACTUALIZAR EL FORMULARIO DESDE LA UI ---
    fun onFirstNameChange(newValue: String) { _firstName.value = newValue }
    fun onLastNameChange(newValue: String) { _lastName.value = newValue }
    fun onBirthDayChange(newValue: String) { _birthDay.value = newValue }
    fun onBirthMonthChange(newValue: String) { _birthMonth.value = newValue }
    fun onBirthYearChange(newValue: String) { _birthYear.value = newValue }
    fun onAddressChange(newValue: String) { _address.value = newValue }
    fun onCityChange(newValue: String) { _city.value = newValue }
    fun onPostalCodeChange(newValue: String) { _postalCode.value = newValue }
    fun onPhonePrefixChange(newValue: String) { _phonePrefix.value = newValue }
    fun onPhoneNumberChange(newValue: String) { _phoneNumber.value = newValue }


    // --- LÓGICA DE RED ---
    fun loadUserProfile(userId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = safeApiCall { api.getUserProfile(userId) }

            if (result is NetworkResult.Success) {
                _userProfile.value = result.data
            }
            _isLoading.value = false
        }
    }

    fun saveProfile() {
        viewModelScope.launch {
            _isLoading.value = true
            _isLoading.value = false
        }
    }
}