package com.israhjf.demoproductreview

import androidx.compose.runtime.Composable
import com.israhjf.demoproductreview.navigation.AppScreen
import com.israhjf.demoproductreview.navigation.Navigation

@Composable
fun MainComposable() {
    val startScreen = AppScreen.Home
    Navigation(startScreen)
}