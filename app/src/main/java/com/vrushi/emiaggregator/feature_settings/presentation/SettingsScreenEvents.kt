package com.vrushi.emiaggregator.feature_settings.presentation

import androidx.core.os.LocaleListCompat

sealed class SettingsScreenEvents {
    data class ShowSnackbar(val message: Int) :
        SettingsScreenEvents()
    data class ChangeAppLanguage(val language: LocaleListCompat):
            SettingsScreenEvents()

}