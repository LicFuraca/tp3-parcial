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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.parcial_grupo_4.R
import com.example.parcial_grupo_4.ui.common.LendlyBottomBar
import com.example.parcial_grupo_4.ui.common.LendlyBottomBarItem
import com.example.parcial_grupo_4.ui.common.LendlyDetailTopBar
import com.example.parcial_grupo_4.ui.common.LendlyTopBar
import com.example.parcial_grupo_4.ui.history.HistoryScreen
import com.example.parcial_grupo_4.ui.history.TransactionDetailScreen
import com.example.parcial_grupo_4.ui.history.TransactionDetailState
import com.example.parcial_grupo_4.ui.history.TransactionDetailViewModel
import com.example.parcial_grupo_4.ui.home.HomeScreen
import com.example.parcial_grupo_4.ui.loans.ActiveLoansScreen
import com.example.parcial_grupo_4.ui.loans.LoanFormScreen
import com.example.parcial_grupo_4.ui.loans.LoanSuccessScreen
import com.example.parcial_grupo_4.ui.loans.LoansScreen
import com.example.parcial_grupo_4.ui.loans.LoansViewModel
import com.example.parcial_grupo_4.ui.manage.ManageScreen
import com.example.parcial_grupo_4.ui.navigation.Routes
import com.example.parcial_grupo_4.ui.shop.ShopScreen

private object TransactionDetailRoutes {
    private const val TransactionDetail = "transaction_detail"
    const val ArgTransactionId = "transactionId"
    val Route = "$TransactionDetail/{$ArgTransactionId}"
    fun build(id: String): String = "$TransactionDetail/${Uri.encode(id)}"
}

private val BottomBarItems = listOf(
    LendlyBottomBarItem(Routes.HOME,    R.string.tab_home,    R.drawable.ic_nav_home),
    LendlyBottomBarItem(Routes.LOANS,   R.string.tab_loan,    R.drawable.ic_nav_loans),
    LendlyBottomBarItem(Routes.SHOP,    R.string.tab_shop,    R.drawable.ic_nav_shop),
    LendlyBottomBarItem(Routes.HISTORY, R.string.tab_history, R.drawable.ic_nav_history),
    LendlyBottomBarItem(Routes.MANAGE,  R.string.tab_manage,  R.drawable.ic_nav_manage),
)

private enum class TopBarStyle { None, Main, Detail }

private data class ScreenChrome(
    val topBar: TopBarStyle,
    val showBottomBar: Boolean,
)

private fun chromeFor(route: String?): ScreenChrome = when (route) {
    TransactionDetailRoutes.Route ->
        ScreenChrome(TopBarStyle.Detail, showBottomBar = false)
    Routes.LOAN_FORM, Routes.LOAN_SUCCESS, Routes.ACTIVE_LOANS ->
        ScreenChrome(TopBarStyle.None, showBottomBar = false)
    else ->
        ScreenChrome(TopBarStyle.Main, showBottomBar = true)
}

private const val TransitionMillis = 300

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val chrome = chromeFor(currentRoute)
    val chromeTransition = fadeIn(animationSpec = tween(TransitionMillis)) togetherWith
        fadeOut(animationSpec = tween(TransitionMillis))

    Scaffold(
        topBar = {
            AnimatedContent(
                targetState = chrome.topBar,
                transitionSpec = { chromeTransition },
                label = "topBar",
            ) { style ->
                when (style) {
                    TopBarStyle.None -> Unit
                    TopBarStyle.Detail -> LendlyDetailTopBar(
                        onBackClick = { navController.popBackStack() },
                    )
                    TopBarStyle.Main -> LendlyTopBar()
                }
            }
        },
        bottomBar = {
            AnimatedContent(
                targetState = chrome.showBottomBar,
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
            startDestination = Routes.HOME,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            enterTransition = { fadeIn(animationSpec = tween(TransitionMillis)) },
            exitTransition = { fadeOut(animationSpec = tween(TransitionMillis)) },
            popEnterTransition = { fadeIn(animationSpec = tween(TransitionMillis)) },
            popExitTransition = { fadeOut(animationSpec = tween(TransitionMillis)) },
        ) {
            composable(Routes.HOME) { HomeScreen() }

            // Loans (nested graph para compartir LoansViewModel)
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

            composable(Routes.SHOP) { ShopScreen() }
            composable(Routes.HISTORY) {
                HistoryScreen(
                    onTransactionClick = { transaction ->
                        navController.navigate(TransactionDetailRoutes.build(transaction.id)) {
                            launchSingleTop = true
                        }
                    },
                )
            }
            composable(Routes.MANAGE) { ManageScreen() }
            composable(
                route = TransactionDetailRoutes.Route,
                arguments = listOf(
                    navArgument(TransactionDetailRoutes.ArgTransactionId) { type = NavType.StringType },
                ),
            ) {
                val detailViewModel: TransactionDetailViewModel = hiltViewModel()
                val state by detailViewModel.state.observeAsState(TransactionDetailState.Loading)
                when (val current = state) {
                    is TransactionDetailState.Found ->
                        TransactionDetailScreen(transaction = current.transaction)
                    TransactionDetailState.NotFound ->
                        LaunchedEffect(Unit) { navController.popBackStack() }
                    TransactionDetailState.Loading -> Unit
                }
            }
        }
    }
}