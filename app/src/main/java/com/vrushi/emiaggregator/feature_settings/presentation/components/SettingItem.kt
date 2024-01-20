package com.vrushi.emiaggregator.feature_settings.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.vrushi.emiaggregator.core.datastore.AppBackupFrequency
import com.vrushi.emiaggregator.core.datastore.AppLanguage
import com.vrushi.emiaggregator.core.datastore.AppTheme
import com.vrushi.emiaggregator.feature_settings.presentation.AboutSettingProvider
import com.vrushi.emiaggregator.feature_settings.presentation.BugReportSettingProvider
import com.vrushi.emiaggregator.feature_settings.presentation.ExportCSVSettingProvider
import com.vrushi.emiaggregator.feature_settings.presentation.ExportDBSettingProvider
import com.vrushi.emiaggregator.feature_settings.presentation.ImportDBSettingProvider
import com.vrushi.emiaggregator.feature_settings.presentation.LanguageSettingProvider
import com.vrushi.emiaggregator.feature_settings.presentation.OutputFolderSettingProvider
import com.vrushi.emiaggregator.feature_settings.presentation.ScheduleBackupSettingProvider
import com.vrushi.emiaggregator.feature_settings.presentation.SettingProvider
import com.vrushi.emiaggregator.feature_settings.presentation.ThemeSettingProvider
import com.vrushi.emiaggregator.feature_settings.presentation.components.dialogs.LanguageDialog
import com.vrushi.emiaggregator.feature_settings.presentation.components.dialogs.ScheduleBackupDialog
import com.vrushi.emiaggregator.feature_settings.presentation.components.dialogs.ThemeDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingItem(
    provider: SettingProvider,
    state: State<Any>?,
    onEvent: (SettingItemEvents) -> Unit
) {
    val scope = rememberCoroutineScope()
    val (languageDialog, showLanguageDialog) = remember {
        mutableStateOf(false)
    }
    val (themeDialog, showThemeDialog) = remember {
        mutableStateOf(false)
    }
    val (scheduleBackupDialog, showScheduleBackupDialog) = remember {
        mutableStateOf(false)
    }
    ListItem(
        modifier = Modifier.clickable {
            when (provider) {
                is LanguageSettingProvider -> {
                    showLanguageDialog(true)
                }

                is ThemeSettingProvider -> {
                    showThemeDialog(true)
                }

                is ScheduleBackupSettingProvider -> {
                    showScheduleBackupDialog(true)
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
        LanguageDialog(
            provider as LanguageSettingProvider,
            state?.value as? AppLanguage,
            onItemClick = {
                onEvent(SettingItemEvents.OnAppLanguageChange(it))
            },
            onDismissRequest = {
                showLanguageDialog(false)
            })
    }
    if (themeDialog) {
        ThemeDialog(
            provider as ThemeSettingProvider,
            state?.value as? AppTheme,
            onItemClick = {
                onEvent(SettingItemEvents.OnAppThemeChange(it))
            },
            onDismissRequest = {
                showThemeDialog(false)
            })
    }
    if (scheduleBackupDialog) {
        ScheduleBackupDialog(
            provider as ScheduleBackupSettingProvider,
            state?.value as? AppBackupFrequency,
            onItemClick = {
                onEvent(SettingItemEvents.OnAppBackupScheduleChange(it))
            },
            onDismissRequest = {
                showScheduleBackupDialog(false)
            })
    }
}