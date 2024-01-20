package com.vrushi.emiaggregator.feature_settings.presentation

import androidx.core.os.LocaleListCompat
import com.vrushi.emiaggregator.core.presentation.util.UiText

sealed class UiSettingsEvents {
    data class ShowSnackbar(val message: UiText) : UiSettingsEvents()

    data class ChangeAppLanguage(val language: LocaleListCompat) : UiSettingsEvents()

    data class ChangeAppTheme(val mode: Int) : UiSettingsEvents()

    object OpenFolderPicker : UiSettingsEvents()

}