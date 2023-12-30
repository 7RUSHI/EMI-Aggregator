package com.vrushi.emiaggregator.core.util

import android.Manifest

sealed class AppConstants {
    companion object Factory {
        private val permissionsToAsk = listOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.MANAGE_EXTERNAL_STORAGE,
            Manifest.permission.POST_NOTIFICATIONS
        )
        val permissionsToRequest = permissionsToAsk.filter { permission ->
            AppExtensions.checkAskPermission(permission)
        }.toTypedArray()

        const val APP_SETTINGS_FILE_NAME = "app-settings.json"
    }
}