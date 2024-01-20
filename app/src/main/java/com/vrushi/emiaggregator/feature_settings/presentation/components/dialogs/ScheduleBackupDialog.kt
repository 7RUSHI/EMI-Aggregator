package com.vrushi.emiaggregator.feature_settings.presentation.components.dialogs

import androidx.compose.runtime.Composable
import com.vrushi.emiaggregator.core.datastore.AppBackupFrequency
import com.vrushi.emiaggregator.feature_settings.presentation.ScheduleBackupSettingProvider

@Composable
fun ScheduleBackupDialog(
    provider: ScheduleBackupSettingProvider,
    selected: AppBackupFrequency?,
    onItemClick: (AppBackupFrequency) -> Unit,
    onDismissRequest: () -> Unit
) {
    SettingDialog(
        title = provider.headline,
        radioOptions = provider.options,
        selected = when (selected) {
            AppBackupFrequency.DAILY -> {
                provider.options[0]
            }

            AppBackupFrequency.WEEKLY -> {
                provider.options[1]
            }

            AppBackupFrequency.MONTHLY -> {
                provider.options[2]
            }

            else -> {
                provider.options[0]
            }
        },
        onItemClick = {
            when (it) {
                provider.options[0] -> {
                    onItemClick(AppBackupFrequency.DAILY)
                }

                provider.options[1] -> {
                    onItemClick(AppBackupFrequency.WEEKLY)
                }

                provider.options[2] -> {
                    onItemClick(AppBackupFrequency.MONTHLY)
                }

                else -> {
                    onItemClick(AppBackupFrequency.DAILY)
                }
            }
        },
        onDismissRequest = onDismissRequest
    )
}
