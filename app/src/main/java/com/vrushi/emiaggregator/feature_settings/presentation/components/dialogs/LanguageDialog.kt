package com.vrushi.emiaggregator.feature_settings.presentation.components.dialogs

import androidx.compose.runtime.Composable
import com.vrushi.emiaggregator.core.datastore.AppLanguage
import com.vrushi.emiaggregator.feature_settings.presentation.LanguageSettingProvider

@Composable
fun LanguageDialog(
    provider: LanguageSettingProvider,
    selected: AppLanguage?,
    onItemClick: (AppLanguage) -> Unit,
    onDismissRequest: () -> Unit
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
                    onItemClick(AppLanguage.ENGLISH)
                }

                provider.options[1] -> {
                    onItemClick(AppLanguage.MARATHI)
                }

                else -> {
                    onItemClick(AppLanguage.ENGLISH)
                }
            }
        },
        onDismissRequest = onDismissRequest
    )
}