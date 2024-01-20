package com.vrushi.emiaggregator.feature_onboard

sealed class OnboardEvents{
    data class OnPermissionResult(val permission: String, val isGranted: Boolean): OnboardEvents()
    object OnAllGrant: OnboardEvents()
}
