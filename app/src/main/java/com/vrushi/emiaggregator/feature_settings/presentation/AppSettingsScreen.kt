package com.vrushi.emiaggregator.feature_settings.presentation

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vrushi.emiaggregator.core.datastore.AppSettings
import com.vrushi.emiaggregator.feature_settings.presentation.components.SettingItem
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppSettingsScreen(
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    appSettingsState: State<AppSettings>,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val appLanguageState = remember {
        derivedStateOf {
            appSettingsState.value.appLanguage
        }
    }
    val appBackupFrequencyState = remember {
        derivedStateOf {
            appSettingsState.value.appBackupFrequency
        }
    }
    val backupFolderState = remember {
        derivedStateOf {
            appSettingsState.value.backupFolder
        }
    }
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        viewModel.grouped.forEach { (category, settingsForCategory) ->
            stickyHeader {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(start = 16.dp),
                    text = stringResource(id = category),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelMedium
                )
            }
            items(settingsForCategory) { settingProvider ->
                val state = when (settingProvider) {
                    is AppLanguageSettingProvider -> {
                        appLanguageState
                    }

                    is AppScheduleBackupSettingProvider -> {
                        appBackupFrequencyState
                    }

                    is OutputFolderSettingProvider -> {
                        backupFolderState
                    }

                    else -> null
                }
                SettingItem(provider = settingProvider, state = state, onEvent = viewModel::onEvent)
            }
        }
    }
    LaunchedEffect(key1 = true) {
        viewModel.sharedFlow.collectLatest { event ->
            when (event) {
                is SettingsScreenEvents.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = context.resources.getString(event.message)
                    )
                }
                is SettingsScreenEvents.ChangeAppLanguage -> {

                    AppCompatDelegate.setApplicationLocales(event.language)
                }
            }
        }
    }
}

