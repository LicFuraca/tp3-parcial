package com.example.parcial_grupo_4.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.parcial_grupo_4.R
import com.example.parcial_grupo_4.ui.common.LendlyBottomBar
import com.example.parcial_grupo_4.ui.common.LendlyBottomBarItem
import com.example.parcial_grupo_4.ui.common.LendlyTopBar
import com.example.parcial_grupo_4.ui.history.HistoryScreen
import com.example.parcial_grupo_4.ui.home.HomeScreen
import com.example.parcial_grupo_4.ui.loans.ActiveLoansScreen
import com.example.parcial_grupo_4.ui.loans.LoanFormScreen
import com.example.parcial_grupo_4.ui.loans.LoanSuccessScreen
import com.example.parcial_grupo_4.ui.loans.LoansScreen
import com.example.parcial_grupo_4.ui.loans.LoansViewModel
import com.example.parcial_grupo_4.ui.manage.ManageScreen
import com.example.parcial_grupo_4.ui.navigation.Routes
import com.example.parcial_grupo_4.ui.shop.ShopScreen

private val BottomBarItems = listOf(
    LendlyBottomBarItem(Routes.HOME,    R.string.tab_home,    R.drawable.ic_nav_home),
    LendlyBottomBarItem(Routes.LOANS,   R.string.tab_loan,    R.drawable.ic_nav_loans),
    LendlyBottomBarItem(Routes.SHOP,    R.string.tab_shop,    R.drawable.ic_nav_shop),
    LendlyBottomBarItem(Routes.HISTORY, R.string.tab_history, R.drawable.ic_nav_history),
    LendlyBottomBarItem(Routes.MANAGE,  R.string.tab_manage,  R.drawable.ic_nav_manage),
)

// Rutas donde NO se muestra el Bottom Bar (sub-pantallas de Loans)
private val RoutesWithoutBottomBar = setOf(
    Routes.LOAN_FORM,
    Routes.LOAN_SUCCESS,
    Routes.ACTIVE_LOANS,
)

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    // Mantiene el tab Loan activo aunque estemos en sub-pantallas
    val bottomBarSelectedRoute = when (currentRoute) {
        Routes.LOAN_FORM, Routes.LOAN_SUCCESS, Routes.ACTIVE_LOANS -> Routes.LOANS
        else -> currentRoute
    }

    Scaffold(
        topBar = {
            if (currentRoute !in RoutesWithoutBottomBar) {
                LendlyTopBar()
            }
        },
        bottomBar = {
            if (currentRoute !in RoutesWithoutBottomBar) {
                LendlyBottomBar(
                    items = BottomBarItems,
                    currentRoute = bottomBarSelectedRoute,
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
            startDestination = Routes.HOME,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
        ) {
            composable(Routes.HOME) { HomeScreen() }

            // ── Loans (nested graph para compartir LoansViewModel) ────────
            navigation(
                startDestination = Routes.LOANS,
                route = Routes.LOANS_GRAPH,
            ) {
                composable(Routes.LOANS) { entry ->
                    val parentEntry = remember(entry) {
                        navController.getBackStackEntry(Routes.LOANS_GRAPH)
                    }
                    val viewModel = hiltViewModel<LoansViewModel>(parentEntry)
                    LoansScreen(viewModel = viewModel, navController = navController)
                }
                composable(Routes.LOAN_FORM) { entry ->
                    val parentEntry = remember(entry) {
                        navController.getBackStackEntry(Routes.LOANS_GRAPH)
                    }
                    val viewModel = hiltViewModel<LoansViewModel>(parentEntry)
                    LoanFormScreen(viewModel = viewModel, navController = navController)
                }
                composable(Routes.LOAN_SUCCESS) { entry ->
                    val parentEntry = remember(entry) {
                        navController.getBackStackEntry(Routes.LOANS_GRAPH)
                    }
                    val viewModel = hiltViewModel<LoansViewModel>(parentEntry)
                    LoanSuccessScreen(viewModel = viewModel, navController = navController)
                }
                composable(Routes.ACTIVE_LOANS) { entry ->
                    val parentEntry = remember(entry) {
                        navController.getBackStackEntry(Routes.LOANS_GRAPH)
                    }
                    val viewModel = hiltViewModel<LoansViewModel>(parentEntry)
                    ActiveLoansScreen(viewModel = viewModel, navController = navController)
                }
            }

            composable(Routes.SHOP)    { ShopScreen() }
            composable(Routes.HISTORY) { HistoryScreen() }
            composable(Routes.MANAGE)  { ManageScreen() }
        }
    }
}
