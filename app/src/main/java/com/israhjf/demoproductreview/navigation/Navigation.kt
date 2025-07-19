package com.israhjf.demoproductreview.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.israhjf.demoproductreview.home.ui.HomeView

@Composable
fun Navigation(startScreen: AppScreen) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startScreen,
    ) {
        composable<AppScreen.Home> {
            HomeView()
        }
    }
}