package com.vrushi.emiaggregator.feature_settings.presentation

import android.net.Uri
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vrushi.emiaggregator.R
import com.vrushi.emiaggregator.core.datastore.AppBackupFrequency
import com.vrushi.emiaggregator.core.datastore.DataStoreRepository
import com.vrushi.emiaggregator.core.presentation.util.UiText
import com.vrushi.emiaggregator.core.util.AppExtensions
import com.vrushi.emiaggregator.feature_settings.presentation.components.SettingItemEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {
    private val settingsProvider = listOf<SettingProvider>(
        LanguageSettingProvider(),
        ThemeSettingProvider(),
        ScheduleBackupSettingProvider(),
        OutputFolderSettingProvider(),
        ExportCSVSettingProvider(),
        ExportDBSettingProvider(),
        ImportDBSettingProvider(),
        BugReportSettingProvider(),
        AboutSettingProvider()
    )
    val grouped = settingsProvider.groupBy { it.category }
    private val _sharedFlow = MutableSharedFlow<UiSettingsEvents>()
    val sharedFlow = _sharedFlow.asSharedFlow()
    fun onEvent(event: SettingItemEvents) {
        when (event) {
            is SettingItemEvents.OnAbout -> {
                viewModelScope.launch {
                    _sharedFlow.emit(UiSettingsEvents.ShowSnackbar(UiText.DynamicString("About click")))
                }
            }

            is SettingItemEvents.OnAppBackupScheduleChange -> {
                viewModelScope.launch {
                    dataStoreRepository.setAppBackupFrequency(event.frequency)
                    val resId = when (event.frequency) {
                        AppBackupFrequency.DAILY -> R.string.snackbar_setting_schedule_daily_success
                        AppBackupFrequency.WEEKLY -> R.string.snackbar_setting_schedule_weekly_success
                        AppBackupFrequency.MONTHLY -> R.string.snackbar_setting_schedule_monthly_success
                    }
                    _sharedFlow.emit(UiSettingsEvents.ShowSnackbar(UiText.StringResource(resId)))
                }
            }

            is SettingItemEvents.OnAppLanguageChange -> {
                val appLocal: LocaleListCompat = AppExtensions.getLanguageTag(event.language)
                viewModelScope.launch {
                    dataStoreRepository.setAppLanguage(event.language)
                    _sharedFlow.emit(UiSettingsEvents.ChangeAppLanguage(appLocal))
                }
            }

            is SettingItemEvents.OnExportAsCSV -> {

            }

            is SettingItemEvents.OnExportDB -> {

            }

            is SettingItemEvents.OnImportDB -> {

            }

            is SettingItemEvents.OnOutputFolderChange -> {
                viewModelScope.launch {
                    _sharedFlow.emit(UiSettingsEvents.OpenFolderPicker)
                }
            }

            is SettingItemEvents.OnBugReport -> {

            }

            is SettingItemEvents.OnAppThemeChange -> {
                val mode = AppExtensions.getThemeMode(event.theme)
                viewModelScope.launch {
                    dataStoreRepository.setAppTheme(event.theme)
                    _sharedFlow.emit(UiSettingsEvents.ChangeAppTheme(mode))
                }
            }
        }
    }

    fun updateOutputFolder(path: Uri?) {
        viewModelScope.launch {
            dataStoreRepository.setBackupFolder(path?.toString() ?: "")
            _sharedFlow.emit(UiSettingsEvents.ShowSnackbar(UiText.DynamicString(path.toString())))
        }
    }
}


interface SettingProvider {
    val category: Int
    val headline: Int
    val support: Int?
    val options: List<Int>
}

class LanguageSettingProvider : SettingProvider {
    override val category: Int = R.string.app_settings_intetface_category
    override val headline: Int = R.string.app_settings_language_headline
    override val support: Int = R.string.app_settings_language_support
    override val options: List<Int> = listOf(
        R.string.app_settings_language_option_english,
        R.string.app_settings_language_option_marathi
    )
}

class ThemeSettingProvider : SettingProvider {
    override val category: Int = R.string.app_settings_intetface_category
    override val headline: Int = R.string.app_settings_theme_headline
    override val support: Int = R.string.app_settings_theme_support
    override val options: List<Int> = listOf(
        R.string.app_settings_theme_option_system,
        R.string.app_settings_theme_option_light,
        R.string.app_settings_theme_option_dark
    )
}

class ScheduleBackupSettingProvider : SettingProvider {
    override val category: Int = R.string.app_settings_backup_category
    override val headline: Int = R.string.app_settings_schedule_headline
    override val support: Int = R.string.app_settings_schedule_support
    override val options: List<Int> = listOf(
        R.string.app_settings_schedule_option_daily,
        R.string.app_settings_schedule_option_weekly,
        R.string.app_settings_schedule_option_monthly
    )
}

class OutputFolderSettingProvider : SettingProvider {
    override val category: Int = R.string.app_settings_backup_category
    override val headline: Int = R.string.app_settings_outputfolder_headline
    override val support: Int = R.string.app_settings_outputfolder_support
    override val options: List<Int> = emptyList()
}

class ExportDBSettingProvider : SettingProvider {
    override val category: Int = R.string.app_settings_database_category
    override val headline: Int = R.string.app_settings_exportdb_headline
    override val support: Int = R.string.app_settings_exportdb_support
    override val options: List<Int> = emptyList()
}

class ExportCSVSettingProvider : SettingProvider {
    override val category: Int = R.string.app_settings_database_category
    override val headline: Int = R.string.app_settings_exportcsv_headline
    override val support: Int = R.string.app_settings_exportcsv_support
    override val options: List<Int> = emptyList()
}

class ImportDBSettingProvider : SettingProvider {
    override val category: Int = R.string.app_settings_database_category
    override val headline: Int = R.string.app_settings_importdata_headline
    override val support: Int = R.string.app_settings_importdata_support
    override val options: List<Int> = emptyList()
}

class BugReportSettingProvider : SettingProvider {
    override val category: Int = R.string.app_settings_troubleshooting_category
    override val headline: Int = R.string.app_settings_bugreport_headline
    override val support: Int? = null
    override val options: List<Int> = emptyList()
}

class AboutSettingProvider : SettingProvider {
    override val category: Int = R.string.app_settings_links_category
    override val headline: Int = R.string.app_settings_about_headline
    override val support: Int? = null
    override val options: List<Int> = emptyList()
}

