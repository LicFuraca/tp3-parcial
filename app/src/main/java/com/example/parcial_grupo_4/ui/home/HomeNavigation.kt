package com.example.parcial_grupo_4.ui.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.parcial_grupo_4.ui.navigation.Routes
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.parcial_grupo_4.ui.shop.ShopRoute
import androidx.navigation.NavGraph.Companion.findStartDestination

fun NavGraphBuilder.homeNavGraph(navController: NavHostController) {
    navigation(
        startDestination = HomeRoute.Home.route,
        route = Routes.HOME,
    ) {
        composable(HomeRoute.Home.route) {
            val viewModel: HomeViewModel = hiltViewModel()

            HomeScreen(
                viewModel = viewModel,
                onCashInClick = {
                    navController.navigate(HomeRoute.CashIn.route)
                },
                onProductClick = { productId ->
                    navController.navigate(Routes.SHOP) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }

                    navController.navigate(ShopRoute.ProductDetail.createRoute(productId))
                },
                onSeeAllProductsClick = {
                    navController.navigate(Routes.SHOP) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onSeeAllLoansClick = {
                    navController.navigate(Routes.ACTIVE_LOANS)
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
                },
                onOptionClick = {
                    navController.navigate(HomeRoute.CashInAmount.route)
                }
            )
        }

        composable(HomeRoute.OverTheCounterCashIn.route) {
            OverTheCounterCashInScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onPartnerClick = {
                    navController.navigate(HomeRoute.CashInAmount.route)
                }
            )
        }

        composable(HomeRoute.CashInAmount.route) {
            CashInAmountScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onNextClick = {
                    navController.navigate(HomeRoute.SuccessfulTransaction.route)
                }
            )
        }

        composable(HomeRoute.SuccessfulTransaction.route) {
            SuccessfulTransactionScreen(
                onCloseClick = {
                    navController.popBackStack()
                },
                onDoneClick = {
                    navController.navigate(HomeRoute.Home.route) {
                        popUpTo(Routes.HOME) {
                            inclusive = false
                        }
                    }
                },
            )
        }

        composable(HomeRoute.Notifications.route) {
            NotificationScreen(
                onBackClick = {
                    navController.popBackStack()
                },
            )
        }
    }
}

