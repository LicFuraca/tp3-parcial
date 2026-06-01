package com.example.parcial_grupo_4.ui

import android.net.Uri
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.parcial_grupo_4.R
import com.example.parcial_grupo_4.ui.common.LendlyBottomBar
import com.example.parcial_grupo_4.ui.common.LendlyBottomBarItem
import com.example.parcial_grupo_4.ui.common.LendlyDetailTopBar
import com.example.parcial_grupo_4.ui.common.LendlyTopBar
import com.example.parcial_grupo_4.ui.history.HistoryScreen
import com.example.parcial_grupo_4.ui.history.TransactionDetailScreen
import com.example.parcial_grupo_4.ui.home.HomeScreen
import com.example.parcial_grupo_4.ui.loans.LoansScreen
import com.example.parcial_grupo_4.ui.manage.ManageScreen
import com.example.parcial_grupo_4.ui.shop.ShopScreen

private object LendlyRoutes {
    const val Home = "home"
    const val Loan = "loan"
    const val Shop = "shop"
    const val History = "history"
    const val Manage = "manage"

    private const val TransactionDetail = "transaction_detail"
    const val ArgTransactionId = "transactionId"
    const val ArgTitle = "title"
    const val ArgAmount = "amount"
    const val ArgCompany = "company"

    val TransactionDetailRoute = "$TransactionDetail/{$ArgTransactionId}" +
        "?$ArgTitle={$ArgTitle}&$ArgAmount={$ArgAmount}&$ArgCompany={$ArgCompany}"

    fun transactionDetail(id: String, title: String, amount: String, company: String): String =
        "$TransactionDetail/${Uri.encode(id)}" +
            "?$ArgTitle=${Uri.encode(title)}" +
            "&$ArgAmount=${Uri.encode(amount)}" +
            "&$ArgCompany=${Uri.encode(company)}"
}

private val BottomBarItems = listOf(
    LendlyBottomBarItem(LendlyRoutes.Home, R.string.tab_home, R.drawable.ic_nav_home),
    LendlyBottomBarItem(LendlyRoutes.Loan, R.string.tab_loan, R.drawable.ic_nav_loans),
    LendlyBottomBarItem(LendlyRoutes.Shop, R.string.tab_shop, R.drawable.ic_nav_shop),
    LendlyBottomBarItem(LendlyRoutes.History, R.string.tab_history, R.drawable.ic_nav_history),
    LendlyBottomBarItem(LendlyRoutes.Manage, R.string.tab_manage, R.drawable.ic_nav_manage),
)

private val RoutesWithoutBottomBar = setOf(LendlyRoutes.TransactionDetailRoute)

private const val ChromeFadeMillis = 700

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val chromeTransition = fadeIn(animationSpec = tween(ChromeFadeMillis)) togetherWith
        fadeOut(animationSpec = tween(ChromeFadeMillis))

    Scaffold(
        topBar = {
            AnimatedContent(
                targetState = currentRoute,
                transitionSpec = { chromeTransition },
                label = "topBar",
            ) { route ->
                when (route) {
                    LendlyRoutes.TransactionDetailRoute -> LendlyDetailTopBar(
                        onBackClick = { navController.popBackStack() },
                    )
                    else -> LendlyTopBar()
                }
            }
        },
        bottomBar = {
            AnimatedContent(
                targetState = currentRoute !in RoutesWithoutBottomBar,
                transitionSpec = { chromeTransition },
                label = "bottomBar",
            ) { showBottomBar ->
                if (showBottomBar) {
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
            composable(LendlyRoutes.Home) { HomeScreen() }
            composable(LendlyRoutes.Loan) { LoansScreen() }
            composable(LendlyRoutes.Shop) { ShopScreen() }
            composable(LendlyRoutes.History) {
                HistoryScreen(
                    onTransactionClick = { transaction ->
                        navController.navigate(
                            LendlyRoutes.transactionDetail(
                                id = transaction.id,
                                title = transaction.title,
                                amount = transaction.amount,
                                company = transaction.subtitleCompany,
                            ),
                        )
                    },
                )
            }
            composable(LendlyRoutes.Manage) { ManageScreen() }
            composable(
                route = LendlyRoutes.TransactionDetailRoute,
                arguments = listOf(
                    navArgument(LendlyRoutes.ArgTransactionId) { type = NavType.StringType },
                    navArgument(LendlyRoutes.ArgTitle) {
                        type = NavType.StringType
                        defaultValue = ""
                    },
                    navArgument(LendlyRoutes.ArgAmount) {
                        type = NavType.StringType
                        defaultValue = ""
                    },
                    navArgument(LendlyRoutes.ArgCompany) {
                        type = NavType.StringType
                        defaultValue = ""
                    },
                ),
            ) { entry ->
                TransactionDetailScreen(
                    title = entry.arguments?.getString(LendlyRoutes.ArgTitle).orEmpty(),
                    amount = entry.arguments?.getString(LendlyRoutes.ArgAmount).orEmpty(),
                    company = entry.arguments?.getString(LendlyRoutes.ArgCompany).orEmpty(),
                )
            }
        }
    }
}
