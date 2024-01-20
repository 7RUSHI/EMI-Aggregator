package com.vrushi.emiaggregator

import androidx.annotation.StringRes

enum class GlobalScreens(val route: String, @StringRes val resourceId: Int?) {
    PermissionsScreen("PermissionsScreen", R.string.permission_app_bar_title)
}

enum class OnBoardingScreens(val route: String, @StringRes val resourceId: Int?) {
    OnBoardingNavRoot("OnBoardingNavRoot", null), AppFlowScreen(
        "AppFlowScreen",
        R.string.app_flow_app_bar_title
    )
}

enum class AppScreens(val route: String, @StringRes val resourceId: Int?) {
    AppScreensNavRoot("AppScreensNavRoot", null), MainScreen(
        "MainScreen",
        R.string.society_app_bar_title
    ),
    SocietyScreen("SocietyScreen", R.string.society_app_bar_title), SettingScreen(
        "SettingScreen",
        R.string.setting_app_bar_title
    )
}

