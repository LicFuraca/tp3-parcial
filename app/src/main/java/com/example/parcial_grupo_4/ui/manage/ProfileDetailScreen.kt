package com.example.parcial_grupo_4.ui.manage

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.parcial_grupo_4.ui.common.LendlyFormHeader
import com.example.parcial_grupo_4.ui.common.LendlyTextField
import com.example.parcial_grupo_4.ui.common.PrimaryButton

@Composable
fun ProfileDetailScreen(
    viewModel: ProfileDetailViewModel,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit
) {
    // Observamos los estados locales del formulario
    val firstName by viewModel.firstName.observeAsState("")
    val lastName by viewModel.lastName.observeAsState("")
    val birthDay by viewModel.birthDay.observeAsState("")
    val birthMonth by viewModel.birthMonth.observeAsState("")
    val birthYear by viewModel.birthYear.observeAsState("")
    val address by viewModel.address.observeAsState("")
    val city by viewModel.city.observeAsState("")
    val postalCode by viewModel.postalCode.observeAsState("")
    val phonePrefix by viewModel.phonePrefix.observeAsState("+65")
    val phoneNumber by viewModel.phoneNumber.observeAsState("")

    // Observamos el perfil que viene de la API
    val profile by viewModel.userProfile.observeAsState()

    // 1. Cargar datos al iniciar
    LaunchedEffect(Unit) {
        viewModel.loadUserProfile("123") // ID temporal
    }

    // 2. Sincronizar datos de API con formulario
    LaunchedEffect(profile) {
        profile?.let { user ->
            val dateParts = user.birthDate.split("-")
            viewModel.onFirstNameChange(user.firstName)
            viewModel.onLastNameChange(user.lastName)
            viewModel.onAddressChange(user.address)
            viewModel.onCityChange(user.city)
            viewModel.onPostalCodeChange(user.postalCode)
            viewModel.onPhonePrefixChange(user.phonePrefix)
            viewModel.onPhoneNumberChange(user.phoneNumber)

            if (dateParts.size == 3) {
                viewModel.onBirthYearChange(dateParts[0])
                viewModel.onBirthMonthChange(dateParts[1])
                viewModel.onBirthDayChange(dateParts[2])
            }
        }
    }

    Scaffold(containerColor = Color(0xFFF9F9F9)) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            LendlyFormHeader(
                title = "Enter your personal\ndetails",
                subtitle = "",
                onBackClick = onBackClick
            )

            Spacer(modifier = Modifier.height(32.dp))

            LendlyTextField(
                value = firstName,
                onValueChange = { viewModel.onFirstNameChange(it) },
                label = "Full legal first and middle name(s)",
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LendlyTextField(
                value = lastName,
                onValueChange = { viewModel.onLastNameChange(it) },
                label = "Full legal last name",
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                LendlyTextField(
                    value = birthDay,
                    onValueChange = { viewModel.onBirthDayChange(it) },
                    label = "Day",
                    modifier = Modifier.weight(1f)
                )
                LendlyTextField(
                    value = birthMonth,
                    onValueChange = { viewModel.onBirthMonthChange(it) },
                    label = "Month",
                    modifier = Modifier.weight(1f)
                )
                LendlyTextField(
                    value = birthYear,
                    onValueChange = { viewModel.onBirthYearChange(it) },
                    label = "Year",
                    modifier = Modifier.weight(1.5f)
                )
            }

            LendlyTextField(
                value = address,
                onValueChange = { viewModel.onAddressChange(it) },
                label = "Address",
                modifier = Modifier.padding(bottom = 16.dp)
            )
            LendlyTextField(
                value = city,
                onValueChange = { viewModel.onCityChange(it) },
                label = "City",
                modifier = Modifier.padding(bottom = 16.dp)
            )
            LendlyTextField(
                value = postalCode,
                onValueChange = { viewModel.onPostalCodeChange(it) },
                label = "Postal Code",
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                LendlyTextField(
                    value = phonePrefix,
                    onValueChange = { viewModel.onPhonePrefixChange(it) },
                    label = "Code",
                    modifier = Modifier.weight(0.3f)
                )
                LendlyTextField(
                    value = phoneNumber,
                    onValueChange = { viewModel.onPhoneNumberChange(it) },
                    label = "Phone Number",
                    modifier = Modifier.weight(0.7f)
                )
            }

            PrimaryButton(
                text = "Next",
                onClick = {
                    viewModel.saveProfile()
                    onSaveClick()
                }
            )
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}