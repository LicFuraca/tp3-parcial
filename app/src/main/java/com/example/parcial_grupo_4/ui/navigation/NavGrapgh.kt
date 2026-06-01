package com.example.parcial_grupo_4.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.parcial_grupo_4.ui.onboarding.OnboardingScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "onboarding"
    ) {
        composable("onboarding"){
            OnboardingScreen(
                navigateToLogin = {/*TODO*/},
                navigateToRegister = {/*TODO*/}
            )
        }
    }
}