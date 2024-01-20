package com.vrushi.emiaggregator.feature_onboard

sealed class UiPermissionsEvents {
    data class ShowSnackbar(val permission: String, val message: Int) :
        UiPermissionsEvents()
}
