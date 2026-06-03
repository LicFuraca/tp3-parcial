package com.example.parcial_grupo_4.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.parcial_grupo_4.ui.MainScreen
import com.example.parcial_grupo_4.ui.onboarding.OnboardingScreen
import com.example.parcial_grupo_4.ui.onboarding.OnboardingViewModel
import com.example.parcial_grupo_4.ui.splash.SplashScreen

@Composable
fun AppNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = "splash" // La app arranca por el Splash Screen
    ) {

        // 1. PANTALLA SPLASH
        composable("splash") {
            SplashScreen(
                onSplashFinished = {
                    // Cuando termina el delay, navega al onboarding y saca el splash del historial (para que al apretar "atrás" no vuelva al splash)
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
                onFinishOnboarding = {
                    // Por ahora, como tus compañeros no hicieron el Login, lo mandamos directo al MainScreen para que puedas probar tu parte de "Manage"
                    navController.navigate("main") {
                        popUpTo("onboarding") { inclusive = true }
                    }
                }
            )
        }

        // 3. PANTALLA PRINCIPAL (Contiene el Bottom Navigation y tu ManageScreen)
        composable("main") {
            MainScreen()
        }
    }
}