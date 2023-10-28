package com.vrushi.emiaggregator

sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
}
