package com.example.parcial_grupo_4.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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
import com.example.parcial_grupo_4.ui.home.HomeRoute
import com.example.parcial_grupo_4.ui.loans.LoansScreen
import com.example.parcial_grupo_4.ui.manage.ManageScreen
import com.example.parcial_grupo_4.ui.shop.ShopScreen
import com.example.parcial_grupo_4.ui.home.homeNavGraph

private object LendlyRoutes {
    const val Home = "home_graph"
    const val Loan = "loan"
    const val Shop = "shop"
    const val History = "history"
    const val Manage = "manage"
}

private val BottomBarItems = listOf(
    LendlyBottomBarItem(LendlyRoutes.Home, R.string.tab_home, R.drawable.ic_nav_home),
    LendlyBottomBarItem(LendlyRoutes.Loan, R.string.tab_loan, R.drawable.ic_nav_loans),
    LendlyBottomBarItem(LendlyRoutes.Shop, R.string.tab_shop, R.drawable.ic_nav_shop),
    LendlyBottomBarItem(LendlyRoutes.History, R.string.tab_history, R.drawable.ic_nav_history),
    LendlyBottomBarItem(LendlyRoutes.Manage, R.string.tab_manage, R.drawable.ic_nav_manage),
)

private val RoutesWithoutTopBar = setOf(
    HomeRoute.CashIn.route,
    HomeRoute.OnlineCashIn.route,
    HomeRoute.OverTheCounterCashIn.route,
    HomeRoute.CashInAmount.route,
    HomeRoute.SuccessfulTransaction.route,
    HomeRoute.Notifications.route,
    HomeRoute.NotificationCalendar.route,
)

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val shouldShowTopBar = currentRoute !in RoutesWithoutTopBar

    Scaffold(
        topBar = {
            if (shouldShowTopBar) {
                LendlyTopBar(
                    onNotificationsClick = {
                        navController.navigate(HomeRoute.Notifications.route)
                    }
                )
            }
        },
        bottomBar = {
            LendlyBottomBar(
                items = BottomBarItems,
                currentRoute = currentRoute,
                onItemSelected = { item ->
                    if (item.route == LendlyRoutes.Home) {
                        navController.navigate(HomeRoute.Home.route) {
                            popUpTo(LendlyRoutes.Home) {
                                inclusive = false
                            }
                            launchSingleTop = true
                        }
                    } else {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
            )
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = LendlyRoutes.Home,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
        ) {
            homeNavGraph(navController)
            composable(LendlyRoutes.Loan) { LoansScreen() }
            composable(LendlyRoutes.Shop) { ShopScreen() }
            composable(LendlyRoutes.History) { HistoryScreen() }
            composable(LendlyRoutes.Manage) { ManageScreen() }
        }
    }
}
