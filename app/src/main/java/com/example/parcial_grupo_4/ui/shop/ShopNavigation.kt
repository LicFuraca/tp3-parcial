package com.example.parcial_grupo_4.ui.shop

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation

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
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Shop Home Screen")
            }
        }
        composable(ShopRoute.Search.route) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Search Screen")
            }
        }
        composable(ShopRoute.Filter.route) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Filter Screen")
            }
        }
        composable(ShopRoute.ProductDetail.route) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Product Detail: $productId")
            }
        }


    }
}