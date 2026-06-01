package com.example.parcial_grupo_4.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.homeNavGraph(navController: NavHostController) {
    navigation(
        startDestination = HomeRoute.Home.route,
        route = "home_graph",
    ) {
        composable(HomeRoute.Home.route) {
            HomeScreen(
                onCashInClick = {
                    navController.navigate(HomeRoute.CashIn.route)
                },
            )
        }

        composable(HomeRoute.CashIn.route) {
            CashInScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onOnlineBankingClick = {
                    navController.navigate(HomeRoute.OnlineCashIn.route)
                },
                onOverTheCounterClick = {
                    navController.navigate(HomeRoute.OverTheCounterCashIn.route)
                },
            )
        }

        composable(HomeRoute.OnlineCashIn.route) {
            PlaceholderHomeScreen(title = "Online Cash-In")
        }

        composable(HomeRoute.OverTheCounterCashIn.route) {
            PlaceholderHomeScreen(title = "Over-the-counter Cash-In")
        }

        composable(HomeRoute.CashInAmount.route) {
            PlaceholderHomeScreen(title = "Cash-In Amount")
        }

        composable(HomeRoute.SuccessfulTransaction.route) {
            PlaceholderHomeScreen(title = "Successful Transaction")
        }

        composable(HomeRoute.Notifications.route) {
            PlaceholderHomeScreen(title = "Notifications")
        }

        composable(HomeRoute.NotificationCalendar.route) {
            PlaceholderHomeScreen(title = "Notification Calendar")
        }
    }
}

@Composable
private fun PlaceholderHomeScreen(title: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = title)
    }
}