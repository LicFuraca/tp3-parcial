package com.example.parcial_grupo_4.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.parcial_grupo_4.ui.MainScreen
import com.example.parcial_grupo_4.ui.auth.AuthViewModel
import com.example.parcial_grupo_4.ui.auth.PantallaCreatePassword
import com.example.parcial_grupo_4.ui.auth.PantallaDone
import com.example.parcial_grupo_4.ui.auth.PantallaFaceRecognition
import com.example.parcial_grupo_4.ui.auth.PantallaIdVerification
import com.example.parcial_grupo_4.ui.auth.PantallaLogin
import com.example.parcial_grupo_4.ui.auth.PantallaProfileDetail
import com.example.parcial_grupo_4.ui.auth.PantallaSignature
import com.example.parcial_grupo_4.ui.auth.PantallaSmsVerification
import com.example.parcial_grupo_4.ui.auth.PantallaVerifiedPage
import com.example.parcial_grupo_4.ui.auth.PantallaVerifyPhone
import com.example.parcial_grupo_4.ui.auth.RegisterViewModel
import com.example.parcial_grupo_4.ui.onboarding.OnboardingScreen
import com.example.parcial_grupo_4.ui.onboarding.OnboardingViewModel
import com.example.parcial_grupo_4.ui.splash.SplashScreen

// Flujo de registro (Sign Up), en el orden del Figma
private object SignupRoutes {
    const val Graph     = "signup_graph"
    const val Phone     = "signup_phone"
    const val Sms       = "signup_sms"
    const val Face      = "signup_face"
    const val Id        = "signup_id"
    const val Verified  = "signup_verified"
    const val Profile   = "signup_profile"
    const val Signature = "signup_signature"
    const val Password  = "signup_password"
    const val Done      = "signup_done"
}

@Composable
fun AppNavGraph(navController: NavHostController = rememberNavController()) {
    // Scopeado a la Activity (fuera del NavHost): sobrevive a la navegación,
    // así el borrado del token en logout() no se cancela al salir de "main".
    val authViewModel: AuthViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = "splash" // La app arranca por el Splash Screen
    ) {

        // 1. PANTALLA SPLASH — decide destino según haya o no sesión persistida
        composable("splash") {
            SplashScreen(
                onNavigateToHome = {
                    navController.navigate("main") {
                        popUpTo("splash") { inclusive = true }
                    }
                },
                onNavigateToOnboarding = {
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
                    // Sign Up: arranca el flujo de registro
                    navController.navigate(SignupRoutes.Graph)
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

        // 4. FLUJO DE REGISTRO (Sign Up) — RegisterViewModel compartido en todo el sub-grafo
        navigation(startDestination = SignupRoutes.Phone, route = SignupRoutes.Graph) {
            composable(SignupRoutes.Phone) { entry ->
                val parentEntry = remember(entry) { navController.getBackStackEntry(SignupRoutes.Graph) }
                val registerVm = hiltViewModel<RegisterViewModel>(parentEntry)
                PantallaVerifyPhone(
                    onNext = { phone ->
                        registerVm.setPhone(phone)
                        navController.navigate(SignupRoutes.Sms)
                    },
                    onBack = { navController.popBackStack() }
                )
            }
            composable(SignupRoutes.Sms) {
                PantallaSmsVerification(
                    onNext = { navController.navigate(SignupRoutes.Face) },
                    onBack = { navController.popBackStack() }
                )
            }
            composable(SignupRoutes.Face) {
                PantallaFaceRecognition(
                    onNext = { navController.navigate(SignupRoutes.Id) },
                    onBack = { navController.popBackStack() }
                )
            }
            composable(SignupRoutes.Id) {
                PantallaIdVerification(
                    onNext = { navController.navigate(SignupRoutes.Verified) },
                    onBack = { navController.popBackStack() }
                )
            }
            composable(SignupRoutes.Verified) {
                PantallaVerifiedPage(
                    onNext = { navController.navigate(SignupRoutes.Profile) },
                    onBack = { navController.popBackStack() }
                )
            }
            composable(SignupRoutes.Profile) {
                PantallaProfileDetail(
                    onNext = { navController.navigate(SignupRoutes.Signature) },
                    onBack = { navController.popBackStack() }
                )
            }
            composable(SignupRoutes.Signature) {
                PantallaSignature(
                    onNext = { navController.navigate(SignupRoutes.Password) },
                    onBack = { navController.popBackStack() }
                )
            }
            composable(SignupRoutes.Password) { entry ->
                val parentEntry = remember(entry) { navController.getBackStackEntry(SignupRoutes.Graph) }
                val registerVm = hiltViewModel<RegisterViewModel>(parentEntry)
                PantallaCreatePassword(
                    onSuccess = { navController.navigate(SignupRoutes.Done) },
                    onBack = { navController.popBackStack() },
                    viewModel = registerVm
                )
            }
            composable(SignupRoutes.Done) {
                PantallaDone(
                    onGetStarted = {
                        // Fin del registro: ya hay sesión guardada → entra a la app y limpia el flujo
                        navController.navigate("main") {
                            popUpTo("onboarding") { inclusive = true }
                        }
                    }
                )
            }
        }

        // 5. PANTALLA PRINCIPAL
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
