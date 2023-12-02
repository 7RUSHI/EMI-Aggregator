package com.vrushi.emiaggregator

sealed class Screen(val route: String) {
}

sealed class GlobalScreens(){
    object AppPermissionsScreen : Screen("app_permissions_screen")
}

sealed class OnBoardingScreens() {
    object NavRoot : Screen("onboarding")
    object AppFlowScreen : Screen("app_flow_screen")
}

sealed class AppScreens() {
    object NavRoot : Screen("Main")
    object MainScreen : Screen("main_screen")
}

