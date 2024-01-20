package com.vrushi.emiaggregator.feature_settings.presentation

import android.provider.DocumentsContract
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import com.vrushi.emiaggregator.core.datastore.AppSettings
import com.vrushi.emiaggregator.feature_settings.presentation.components.SettingItem
import kotlinx.coroutines.flow.collectLatest
import java.io.FileNotFoundException

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppSettingsScreen(
    snackbarHostState: SnackbarHostState,
    appSettingsState: State<AppSettings>,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val tag = "AppSettingsScreen"
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
    val appThemeState = remember {
        derivedStateOf {
            appSettingsState.value.appTheme
        }
    }
    val pickOutputFolder = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocumentTree(),
        onResult = { folderUri ->
            if(folderUri != null) {
                try {
                    val dirUri = DocumentsContract.buildDocumentUriUsingTree(folderUri, DocumentsContract.getTreeDocumentId(folderUri))
                    val fileUri = DocumentsContract.createDocument(context.contentResolver, dirUri, "text/plain", "test.txt")
                    Log.d(tag, "AppSettingsScreen: ${fileUri?.path}")
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                }
            }
        })
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
                    style = MaterialTheme.typography.labelLarge
                )
            }
            items(settingsForCategory) { settingProvider ->
                val state = when (settingProvider) {
                    is LanguageSettingProvider -> {
                        appLanguageState
                    }

                    is ScheduleBackupSettingProvider -> {
                        appBackupFrequencyState
                    }

                    is OutputFolderSettingProvider -> {
                        backupFolderState
                    }

                    is ThemeSettingProvider -> {
                        appThemeState
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
                is UiSettingsEvents.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }

                is UiSettingsEvents.ChangeAppLanguage -> {
                    AppCompatDelegate.setApplicationLocales(event.language)
                }

                is UiSettingsEvents.ChangeAppTheme -> {
                    AppCompatDelegate.setDefaultNightMode(event.mode)
                }

                is UiSettingsEvents.OpenFolderPicker -> {
                    pickOutputFolder.launch(null)
                }
            }
        }
    }
}

