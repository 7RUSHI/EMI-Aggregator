package com.vrushi.emiaggregator.core.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.os.LocaleListCompat
import androidx.datastore.dataStore
import com.vrushi.emiaggregator.core.datastore.AppLanguage
import com.vrushi.emiaggregator.core.datastore.AppSettingsSerializer
import com.vrushi.emiaggregator.core.datastore.AppTheme

sealed class AppExtensions {
    companion object {
        fun checkAskPermission(permission: String): Boolean {
            val androidVersion = Build.VERSION.SDK_INT
            return when (permission) {

                Manifest.permission.POST_NOTIFICATIONS -> {
                    androidVersion >= Build.VERSION_CODES.TIRAMISU
                }

                else -> false
            }
        }

        fun Context.checkAppPermissionGranted(): Boolean {
            val context = this
            return AppConstants.permissionsToRequest.all { permission ->
                ContextCompat.checkSelfPermission(
                    context, permission
                ) == PackageManager.PERMISSION_GRANTED
            }
        }

        val Context.appSettingsDataStore by dataStore(
            AppConstants.APP_SETTINGS_FILE_NAME, AppSettingsSerializer
        )

        fun Context.findActivity(): Activity {
            var context = this
            while (context is ContextWrapper) {
                if (context is Activity) return context
                context = context.baseContext
            }
            throw IllegalStateException("no activity")
        }

        fun Activity.openAppSettings() {
            Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package", packageName, null)
            ).also(::startActivity)
        }

        fun getLanguageTag(language: AppLanguage): LocaleListCompat {
            return when (language) {
                AppLanguage.ENGLISH -> {
                    LocaleListCompat.forLanguageTags("en")
                }

                AppLanguage.MARATHI -> {
                    LocaleListCompat.forLanguageTags("mr")
                }
            }
        }

        fun getThemeMode(theme: AppTheme): Int {
            return when (theme) {
                AppTheme.SYSTEM -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                AppTheme.LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
                AppTheme.DARK -> AppCompatDelegate.MODE_NIGHT_YES
            }
        }
    }

}
