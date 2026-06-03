package com.example.parcial_grupo_4.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.parcial_grupo_4.ui.MainScreen
import com.example.parcial_grupo_4.ui.auth.AuthViewModel
import com.example.parcial_grupo_4.ui.auth.PantallaLogin
import com.example.parcial_grupo_4.ui.onboarding.OnboardingScreen
import com.example.parcial_grupo_4.ui.onboarding.OnboardingViewModel
import com.example.parcial_grupo_4.ui.splash.SplashScreen

@Composable
fun AppNavGraph(navController: NavHostController = rememberNavController()) {
    // Scopeado a la Activity (fuera del NavHost): sobrevive a la navegación,
    // así el borrado del token en logout() no se cancela al salir de "main".
    val authViewModel: AuthViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = "splash" // La app arranca por el Splash Screen
    ) {

        // 1. PANTALLA SPLASH
        composable("splash") {
            SplashScreen(
                onSplashFinished = {
                    // Cuando termina el delay, navega al onboarding y saca el splash del historial
                    navController.navigate("onboarding") {
                        popUpTo("splash") { inclusive = true }
                    }
                }
            )
        }

        // 2. PANTALLA ONBOARDING
        composable("onboarding") {
            val viewModel: OnboardingViewModel = viewModel()
            OnboardingScreen(
                viewModel = viewModel,
                onNavigateToLogin = { navController.navigate("login") },
                onFinishOnboarding = {
                    // Sign Up: por ahora va directo al Main (flujo de registro pendiente de cablear)
                    navController.navigate("main") {
                        popUpTo("onboarding") { inclusive = true }
                    }
                }
            )
        }

        // 3. PANTALLA LOGIN
        composable("login") {
            PantallaLogin(
                onNext = {
                    // Login OK: vamos al Main y limpiamos splash/onboarding/login del historial
                    navController.navigate("main") {
                        popUpTo("onboarding") { inclusive = true }
                    }
                }
            )
        }

        // 4. PANTALLA PRINCIPAL
        composable("main") {
            MainScreen(
                onLogout = {
                    authViewModel.logout()
                    navController.navigate("onboarding") {
                        popUpTo("main") { inclusive = true }
                    }
                }
            )
        }
    }
}
