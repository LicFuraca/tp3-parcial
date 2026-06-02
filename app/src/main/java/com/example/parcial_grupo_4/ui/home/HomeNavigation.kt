package com.example.parcial_grupo_4.ui.home

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
            OnlineCashInScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(HomeRoute.OverTheCounterCashIn.route) {
            OverTheCounterCashInScreen()
        }

        composable(HomeRoute.CashInAmount.route) {
            CashInAmountScreen()
        }

        composable(HomeRoute.SuccessfulTransaction.route) {
            SuccessfulTransactionScreen()
        }

        composable(HomeRoute.Notifications.route) {
            NotificationScreen()
        }

        composable(HomeRoute.NotificationCalendar.route) {
            NotificationCalendarScreen()
        }
    }
}

