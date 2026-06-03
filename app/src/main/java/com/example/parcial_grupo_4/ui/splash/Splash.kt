package com.example.parcial_grupo_4.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay
import com.example.parcial_grupo_4.R

private const val SplashMinDelayMillis = 2500L

@Composable
fun SplashScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToOnboarding: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val isLoggedIn by viewModel.isLoggedIn.observeAsState()
    var minTimePassed by remember { mutableStateOf(false) }

    // Mantenemos el tiempo mínimo de marca en pantalla aunque el chequeo de sesión sea instantáneo
    LaunchedEffect(Unit) {
        delay(SplashMinDelayMillis)
        minTimePassed = true
    }

    // Navegamos solo cuando pasó el delay Y ya sabemos si hay sesión
    LaunchedEffect(minTimePassed, isLoggedIn) {
        val loggedIn = isLoggedIn
        if (minTimePassed && loggedIn != null) {
            if (loggedIn) onNavigateToHome() else onNavigateToOnboarding()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.touring_green)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_lendly),
            contentDescription = "Lendly Logo",
            modifier = Modifier.fillMaxWidth(0.5f),
            contentScale = ContentScale.Fit
        )
    }
}
