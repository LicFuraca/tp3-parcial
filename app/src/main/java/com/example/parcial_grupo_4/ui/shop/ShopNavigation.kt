package com.example.parcial_grupo_4.ui.shop

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.parcial_grupo_4.data.model.Product
import com.example.parcial_grupo_4.data.model.ShopResponse


sealed class ShopRoute(val route: String) {
    object Home : ShopRoute("shop_home")
    object Search : ShopRoute("shop_search")
    object Filter : ShopRoute("shop_filter")
    object ProductDetail : ShopRoute("shop_product_detail/{productId}") {
        fun createRoute(productId: String) = "shop_product_detail/$productId"
    }
}

fun NavGraphBuilder.shopNavGraph(navController: NavHostController) {
    navigation(
        startDestination = ShopRoute.Home.route,
        route = "shop_graph"
    ) {
        composable(ShopRoute.Home.route) {
            val viewModel: ShopViewModel = hiltViewModel()
            ShopHomeScreen(
                viewModel = viewModel,
                onProductClick = { productId ->
                    navController.navigate(ShopRoute.ProductDetail.createRoute(productId))
                },
                onSearchClick = {
                    navController.navigate(ShopRoute.Search.route)
                },
                onFilterClick = {
                    navController.navigate(ShopRoute.Filter.route)
                }
            )
        }
        composable(ShopRoute.Search.route) { backStackEntry ->
            val parentEntry = remember(backStackEntry) { navController.getBackStackEntry("shop_graph") }
            val viewModel: ShopViewModel = hiltViewModel(parentEntry)

            SearchScreen(
                onBackClick = {
                    viewModel.searchProducts("") //
                    navController.popBackStack()
                },
                onSearchSubmit = { query ->
                    viewModel.searchProducts(query) //
                    navController.popBackStack()
                }
            )
        }
        composable(ShopRoute.Filter.route) {
            FilterScreen(
                onBackClick = { navController.popBackStack() },
                onApplyFilters = {
                    navController.popBackStack()
                }
            )
        }
        composable(ShopRoute.ProductDetail.route) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId").orEmpty()
            val parentEntry = remember(backStackEntry) { navController.getBackStackEntry("shop_graph")}
            val viewModel: ShopViewModel = hiltViewModel(parentEntry)
            val shopData: ShopResponse? by viewModel.shopData.observeAsState()
            val product = shopData?.products?.find { it.id == productId }
                ?: shopData?.featured?.find { it.id == productId }
            val error by viewModel.error.observeAsState()

            when {
                product != null -> ProductDetailScreen(product, onBackClick = { navController.popBackStack() })
                error != null -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = error!!)
                    }
                }
                shopData != null -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "Product not found")
                    }
                }
                else -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}
