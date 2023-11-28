package com.vrushi.emiaggregator.feature_onboard.presentation

sealed class OnboardEvents{
    data class OnPermissionResult(val permission: String, val isGranted: Boolean): OnboardEvents()
    data object OnAllGrant: OnboardEvents()
}
