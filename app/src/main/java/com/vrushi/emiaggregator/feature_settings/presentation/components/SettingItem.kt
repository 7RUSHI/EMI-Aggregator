package com.vrushi.emiaggregator.feature_settings.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.vrushi.emiaggregator.core.datastore.AppBackupFrequency
import com.vrushi.emiaggregator.core.datastore.AppLanguage
import com.vrushi.emiaggregator.feature_settings.presentation.AboutSettingProvider
import com.vrushi.emiaggregator.feature_settings.presentation.AppLanguageSettingProvider
import com.vrushi.emiaggregator.feature_settings.presentation.AppScheduleBackupSettingProvider
import com.vrushi.emiaggregator.feature_settings.presentation.AppThemeSettingProvider
import com.vrushi.emiaggregator.feature_settings.presentation.BugReportSettingProvider
import com.vrushi.emiaggregator.feature_settings.presentation.ExportCSVSettingProvider
import com.vrushi.emiaggregator.feature_settings.presentation.ExportDBSettingProvider
import com.vrushi.emiaggregator.feature_settings.presentation.ImportDBSettingProvider
import com.vrushi.emiaggregator.feature_settings.presentation.OutputFolderSettingProvider
import com.vrushi.emiaggregator.feature_settings.presentation.SettingProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingItem(
    provider: SettingProvider,
    state: State<Any>?,
    onEvent: (SettingItemEvents) -> Unit
) {
    val scope = rememberCoroutineScope()
    val (languageDialog,showLanguageDialog) = remember {
        mutableStateOf(false)
    }
    val themeDialog by remember {
        mutableStateOf(false)
    }
    val scheduleBackupDialog by remember {
        mutableStateOf(false)
    }
    val options: List<Int>?
    ListItem(
        modifier = Modifier.clickable {
            when (provider) {
                is AppLanguageSettingProvider -> {
                    showLanguageDialog(true)
                }

                is AppThemeSettingProvider -> {

                }

                is AppScheduleBackupSettingProvider -> {
                    onEvent(SettingItemEvents.OnAppBackupScheduleChange(AppBackupFrequency.WEEKLY))
                }

                is OutputFolderSettingProvider -> {
                    onEvent(SettingItemEvents.OnOutputFolderChange)
                }

                is ExportDBSettingProvider -> {
                    onEvent(SettingItemEvents.OnExportDB)
                }

                is ExportCSVSettingProvider -> {
                    onEvent(SettingItemEvents.OnExportAsCSV)
                }

                is ImportDBSettingProvider -> {
                    onEvent(SettingItemEvents.OnImportDB)
                }

                is BugReportSettingProvider -> {
                    onEvent(SettingItemEvents.OnBugReport)
                }

                is AboutSettingProvider -> {
                    onEvent(SettingItemEvents.OnAbout)
                }
            }
        },
        headlineText = {
            Text(
                text = stringResource(id = provider.headline),
                style = MaterialTheme.typography.bodyLarge
            )
        },
        supportingText = if (provider.support != null) {
            {
                Text(
                    text = stringResource(id = provider.support as Int),
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    )
                )
            }
        } else {
            null
        }
    )
    if (languageDialog) {
        AppLanguageDialog(provider, state?.value as? AppLanguage,onEvent, onDismissRequest = {
            showLanguageDialog(false)
        })
    }
}

@Composable
fun AppLanguageDialog(
    provider: SettingProvider,
    selected: AppLanguage?,
    onEvent: (SettingItemEvents) -> Unit,
    onDismissRequest: () -> Unit,
) {
    SettingDialog(
        title = provider.headline,
        radioOptions = provider.options,
        selected = when (selected) {
            AppLanguage.ENGLISH -> {
                provider.options[0]
            }

            AppLanguage.MARATHI -> {
                provider.options[1]
            }

            else -> {
                provider.options[0]
            }
        },
        onItemClick = {
            when (it) {
                provider.options[0] -> {
                    onEvent(SettingItemEvents.OnAppLanguageChange(AppLanguage.ENGLISH))
                }

                provider.options[1] -> {
                    onEvent(SettingItemEvents.OnAppLanguageChange(AppLanguage.MARATHI))
                }

                else -> {
                    onEvent(SettingItemEvents.OnAppLanguageChange(AppLanguage.ENGLISH))
                }
            }
        },
        onDismissRequest = onDismissRequest
    )
}
