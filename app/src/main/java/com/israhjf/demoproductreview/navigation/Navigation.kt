package com.israhjf.demoproductreview.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.israhjf.demoproductreview.home.ui.HomeView
import com.israhjf.demoproductreview.productDetails.ui.ProductDetailsView

@Composable
fun Navigation(startScreen: AppScreen) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startScreen,
    ) {
        composable<AppScreen.Home> {
            HomeView(
                onProductClick = { productId ->
                    navController.navigate(AppScreen.ProductDetails(productId))
                }
            )
        }
        composable<AppScreen.ProductDetails> { backStackEntry ->
            val arg = backStackEntry.toRoute<AppScreen.ProductDetails>()
            ProductDetailsView(
                onBack = { navController.popBackStack() },
                productId = arg.productId
            )
        }
    }
}