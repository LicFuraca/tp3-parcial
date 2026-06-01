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
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
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
import com.example.parcial_grupo_4.ui.history.TransactionDetailState
import com.example.parcial_grupo_4.ui.history.TransactionDetailViewModel
import com.example.parcial_grupo_4.ui.home.HomeScreen
import com.example.parcial_grupo_4.ui.loans.LoansScreen
import com.example.parcial_grupo_4.ui.manage.ManageScreen
import com.example.parcial_grupo_4.ui.shop.ShopScreen
import com.example.parcial_grupo_4.ui.shop.shopNavGraph

private object LendlyRoutes {
    const val Home = "home"
    const val Loan = "loan"
    const val Shop = "shop"
    const val History = "history"
    const val Manage = "manage"

    private const val TransactionDetail = "transaction_detail"
    const val ArgTransactionId = "transactionId"

    val TransactionDetailRoute = "$TransactionDetail/{$ArgTransactionId}"

    fun transactionDetail(id: String): String = "$TransactionDetail/${Uri.encode(id)}"
}

private val BottomBarItems = listOf(
    LendlyBottomBarItem(LendlyRoutes.Home, R.string.tab_home, R.drawable.ic_nav_home),
    LendlyBottomBarItem(LendlyRoutes.Loan, R.string.tab_loan, R.drawable.ic_nav_loans),
    LendlyBottomBarItem(LendlyRoutes.Shop, R.string.tab_shop, R.drawable.ic_nav_shop),
    LendlyBottomBarItem(LendlyRoutes.History, R.string.tab_history, R.drawable.ic_nav_history),
    LendlyBottomBarItem(LendlyRoutes.Manage, R.string.tab_manage, R.drawable.ic_nav_manage),
)

private enum class TopBarStyle { Main, Detail }

/**
 * Define qué "chrome" (top bar + bottom bar) corresponde a cada ruta. Centralizarlo
 * acá lo hace escalable: para una pantalla nueva sólo se agrega un caso, y la
 * animación de las barras se dispara únicamente cuando cambia el *tipo* de chrome
 * —no en cada navegación entre tabs que comparten el mismo layout—.
 */
private data class ScreenChrome(
    val topBar: TopBarStyle,
    val showBottomBar: Boolean,
)

private fun chromeFor(route: String?): ScreenChrome = when (route) {
    LendlyRoutes.TransactionDetailRoute -> ScreenChrome(TopBarStyle.Detail, showBottomBar = false)
    else -> ScreenChrome(TopBarStyle.Main, showBottomBar = true)
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
            startDestination = LendlyRoutes.Home,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            enterTransition = { fadeIn(animationSpec = tween(TransitionMillis)) },
            exitTransition = { fadeOut(animationSpec = tween(TransitionMillis)) },
            popEnterTransition = { fadeIn(animationSpec = tween(TransitionMillis)) },
            popExitTransition = { fadeOut(animationSpec = tween(TransitionMillis)) },
        ) {
            composable(LendlyRoutes.Home) { HomeScreen() }
            composable(LendlyRoutes.Loan) { LoansScreen() }
            shopNavGraph(navController)


            composable(LendlyRoutes.History) {
                HistoryScreen(
                    onTransactionClick = { transaction ->
                        navController.navigate(LendlyRoutes.transactionDetail(transaction.id)) {
                            launchSingleTop = true
                        }
                    },
                )
            }
            composable(LendlyRoutes.Manage) { ManageScreen() }
            composable(
                route = LendlyRoutes.TransactionDetailRoute,
                arguments = listOf(
                    navArgument(LendlyRoutes.ArgTransactionId) { type = NavType.StringType },
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
