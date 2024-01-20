package com.vrushi.emiaggregator.feature_settings.presentation.components.dialogs

import androidx.compose.runtime.Composable
import com.vrushi.emiaggregator.core.datastore.AppTheme
import com.vrushi.emiaggregator.feature_settings.presentation.ThemeSettingProvider

@Composable
fun ThemeDialog(
    provider: ThemeSettingProvider,
    selected: AppTheme?,
    onItemClick: (AppTheme) -> Unit,
    onDismissRequest: () -> Unit
) {
    SettingDialog(
        title = provider.headline,
        radioOptions = provider.options,
        selected = when (selected) {
            AppTheme.SYSTEM -> {
                provider.options[0]
            }

            AppTheme.LIGHT -> {
                provider.options[1]
            }

            AppTheme.DARK -> {
                provider.options[2]
            }

            else -> {
                provider.options[0]
            }
        },
        onItemClick = {
            when (it) {
                provider.options[0] -> {
                    onItemClick(AppTheme.SYSTEM)
                }

                provider.options[1] -> {
                    onItemClick(AppTheme.LIGHT)
                }

                provider.options[2] -> {
                    onItemClick(AppTheme.DARK)
                }

                else -> {
                    onItemClick(AppTheme.SYSTEM)
                }
            }
        },
        onDismissRequest = onDismissRequest
    )
}