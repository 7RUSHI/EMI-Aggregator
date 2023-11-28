package com.vrushi.emiaggregator.feature_onboard.presentation

data class OnboardState(
    var isAllPermissionsAccepted: Boolean = false,
    var showPermissionDialog: Boolean = false
)
