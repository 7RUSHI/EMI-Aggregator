package com.vrushi.emiaggregator.feature_onboard.presentation

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vrushi.emiaggregator.R
import com.vrushi.emiaggregator.core.presentation.util.findActivity
import com.vrushi.emiaggregator.core.presentation.util.openAppSettings
import com.vrushi.emiaggregator.core.util.AppConstants
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AppPermissionsScreen(
    state: OnboardState,
    snackbarHostState: SnackbarHostState,
    onEvent: (OnboardEvents) -> Unit,
    onOnboardingFinished: () -> Unit,
    sharedFlow: SharedFlow<ScreenEvents>
) {
    val context = LocalContext.current
    val allAppPermissionsResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = permissionLauncher@{ perms ->
            val isAllGranted = AppConstants.permissionsToRequest.all { permission ->
                perms[permission] == true
            }
            if (isAllGranted) {
                onEvent(OnboardEvents.OnAllGrant)
                return@permissionLauncher
            }
            AppConstants.permissionsToRequest.forEach { permission ->
                onEvent(
                    OnboardEvents.OnPermissionResult(
                        permission = permission,
                        isGranted = perms[permission] == true
                    )
                )
            }
        }
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(stringResource(id = R.string.app_flow_detail_text), textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(8.dp))
        Row() {
            Button(enabled = !state.isAllPermissionsAccepted, onClick = {
                allAppPermissionsResultLauncher.launch(AppConstants.permissionsToRequest)
            }) {
                Text(
                    text = stringResource(id = R.string.app_permissions_button_allow)
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
            Button(enabled = state.isAllPermissionsAccepted, onClick = {
                onOnboardingFinished()
            }) {
                Text(
                    text = stringResource(id = R.string.app_permissions_button_start)
                )
            }
        }
    }
    LaunchedEffect(key1 = true) {
        sharedFlow.collectLatest { event ->
            when (event) {
                is ScreenEvents.ShowSnackbar -> {
                    val isPermanentDeclined = !context.findActivity()
                        .shouldShowRequestPermissionRationale(event.permission)
                    if (isPermanentDeclined) {
                        val result = snackbarHostState.showSnackbar(
                            message = context.resources.getString(event.message),
                            actionLabel = context.resources.getString(R.string.app_permissions_snackbar_action_label_goto)
                        )
                        when (result) {
                            SnackbarResult.Dismissed -> {}
                            SnackbarResult.ActionPerformed -> {
                                context.findActivity().openAppSettings()
                            }
                        }
                    } else {
                        snackbarHostState.showSnackbar(
                            message = context.resources.getString(event.message)
                        )
                    }

                }
            }
        }
    }
}
/*
    PermissionDialog(
        permissionTextProvider = when (permission) {
            Manifest.permission.POST_NOTIFICATIONS -> {
                PostNotificationsPermissionTextProvider()
            }

            Manifest.permission.MANAGE_EXTERNAL_STORAGE -> {
                ManageExternalStoragePermissionTextProvider()
            }

            Manifest.permission.READ_EXTERNAL_STORAGE -> {
                ReadExternalStoragePermissionTextProvider()
            }

            Manifest.permission.WRITE_EXTERNAL_STORAGE -> {
                WriteExternalStoragePermissionTextProvider()
            }

            else -> return
        },
        isPermanentDeclined = !context.findActivity()
            .shouldShowRequestPermissionRationale(permission),
        onDismissRequest = {},
        onConfirmation = {},
        onGoToAppSettings = {
            context.findActivity().openAppSettings()
        }
    )*/

