package com.vrushi.emiaggregator.feature_onboard

data class OnboardState(
    var isAllPermissionsAccepted: Boolean = false,
    var showPermissionDialog: Boolean = false
)
