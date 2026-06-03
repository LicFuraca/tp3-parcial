package com.example.parcial_grupo_4.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.parcial_grupo_4.R
import com.example.parcial_grupo_4.ui.common.LendlyBottomBar
import com.example.parcial_grupo_4.ui.common.LendlyBottomBarItem
import com.example.parcial_grupo_4.ui.common.LendlyTopBar
import com.example.parcial_grupo_4.ui.history.HistoryScreen
import com.example.parcial_grupo_4.ui.home.HomeScreen
import com.example.parcial_grupo_4.ui.loans.LoansScreen
import com.example.parcial_grupo_4.ui.manage.CreditScoreScreen
import com.example.parcial_grupo_4.ui.manage.DoneScreen
import com.example.parcial_grupo_4.ui.manage.ManageScreen
import com.example.parcial_grupo_4.ui.manage.ProfileDetailScreen
import com.example.parcial_grupo_4.ui.manage.ProfileDetailViewModel
import com.example.parcial_grupo_4.ui.shop.ShopScreen

private object LendlyRoutes {
    const val Home = "home"
    const val Loan = "loan"
    const val Shop = "shop"
    const val History = "history"
    const val Manage = "manage"
    // Nuevas rutas para la sub-navegación de Manage
    const val ProfileDetail = "profile_detail"
    const val Done = "done"
    const val CreditScore = "credit_score"
}

private val BottomBarItems = listOf(
    LendlyBottomBarItem(LendlyRoutes.Home, R.string.tab_home, R.drawable.ic_nav_home),
    LendlyBottomBarItem(LendlyRoutes.Loan, R.string.tab_loan, R.drawable.ic_nav_loans),
    LendlyBottomBarItem(LendlyRoutes.Shop, R.string.tab_shop, R.drawable.ic_nav_shop),
    LendlyBottomBarItem(LendlyRoutes.History, R.string.tab_history, R.drawable.ic_nav_history),
    LendlyBottomBarItem(LendlyRoutes.Manage, R.string.tab_manage, R.drawable.ic_nav_manage),
)

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    // Ocultar barras superior e inferior en las pantallas de detalle de Manage
    val isMainTab = BottomBarItems.any { it.route == currentRoute }

    Scaffold(
        topBar = {
            if (isMainTab) { LendlyTopBar() }
        },
        bottomBar = {
            if (isMainTab) {
                LendlyBottomBar(
                    items = BottomBarItems,
                    currentRoute = currentRoute,
                    onItemSelected = { item ->
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                )
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = LendlyRoutes.Home,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
        ) {
            // Rutas Principales
            composable(LendlyRoutes.Home) { HomeScreen() }
            composable(LendlyRoutes.Loan) { LoansScreen() }
            composable(LendlyRoutes.Shop) { ShopScreen() }
            composable(LendlyRoutes.History) { HistoryScreen() }

            // Ruta Base de Manage
            composable(LendlyRoutes.Manage) {
                ManageScreen(
                    onEditProfileClick = { navController.navigate(LendlyRoutes.ProfileDetail) },
                    onCreditScoreClick = { navController.navigate(LendlyRoutes.CreditScore) },
                    onLogoutClick = { /* Por ahora vacío, luego navegará al Login */ }
                )
            }

            // Sub-rutas de Manage
            composable(LendlyRoutes.ProfileDetail) {
                // Instanciamos el ViewModel acá, atado al ciclo de vida de la pantalla
                val viewModel: ProfileDetailViewModel = viewModel()
                ProfileDetailScreen(
                    viewModel = viewModel,
                    onBackClick = { navController.popBackStack() }, // Vuelve atrás
                    onSaveClick = { navController.navigate(LendlyRoutes.Done) } // Va a la pantalla Done
                )
            }

            composable(LendlyRoutes.Done) {
                DoneScreen(
                    onDoneClick = {
                        // Vuelve a la pantalla base de Manage limpiando el historial intermedio
                        navController.popBackStack(LendlyRoutes.Manage, inclusive = false)
                    }
                )
            }

            composable(LendlyRoutes.CreditScore) {
                CreditScoreScreen(
                    onBackClick = { navController.popBackStack() } // Vuelve atrás
                )
            }
        }
    }
}