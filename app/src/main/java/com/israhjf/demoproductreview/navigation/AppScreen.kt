package com.israhjf.demoproductreview.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class AppScreen {

    @Serializable
    data object Home: AppScreen()
    @Serializable
    data object ProductDetails: AppScreen()
}