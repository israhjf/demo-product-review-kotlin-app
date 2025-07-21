package com.israhjf.demoproductreview.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class AppScreen {

    @Serializable
    data object Home: AppScreen()
    @Serializable
    class ProductDetails(val productId: Int): AppScreen()
}