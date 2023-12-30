package com.vrushi.emiaggregator.feature_onboard.presentation

sealed class PermissionsScreenEvents {
    data class ShowSnackbar(val permission: String, val message: Int) :
        PermissionsScreenEvents()
}
