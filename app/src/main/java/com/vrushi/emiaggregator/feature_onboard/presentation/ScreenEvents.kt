package com.vrushi.emiaggregator.feature_onboard.presentation

sealed class ScreenEvents {
    data class ShowSnackbar(val permission: String, val message: Int) :
        ScreenEvents()
}
