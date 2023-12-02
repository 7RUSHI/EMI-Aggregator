package com.vrushi.emiaggregator.core.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.datastore.dataStore
import com.vrushi.emiaggregator.core.datastore.AppSettingsSerializer

sealed class AppExtensions {
    companion object {
        fun checkAskPermission(permission: String): Boolean {
            val androidVersion = Build.VERSION.SDK_INT
            return when (permission) {
                Manifest.permission.READ_EXTERNAL_STORAGE -> {
                    androidVersion < Build.VERSION_CODES.TIRAMISU
                }

                Manifest.permission.WRITE_EXTERNAL_STORAGE -> {
                    androidVersion < Build.VERSION_CODES.R
                }

                Manifest.permission.MANAGE_EXTERNAL_STORAGE -> {
                    androidVersion >= Build.VERSION_CODES.R
                }

                Manifest.permission.POST_NOTIFICATIONS -> {
                    androidVersion >= Build.VERSION_CODES.TIRAMISU
                }

                else -> false
            }
        }

        fun Context.checkAppPermissionGranted(): Boolean {
            val context = this
            return AppConstants.permissionsToRequest.all { permission ->
                ContextCompat.checkSelfPermission(context,permission) == PackageManager.PERMISSION_GRANTED
            }
        }

        val Context.appSettingsDataStore by dataStore("app-settings.json", AppSettingsSerializer)
    }

}
