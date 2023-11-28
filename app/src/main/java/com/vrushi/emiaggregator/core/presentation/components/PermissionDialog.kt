package com.vrushi.emiaggregator.core.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vrushi.emiaggregator.R

@Composable
fun PermissionDialog(
    permissionTextProvider: PermissionTextProvider,
    isPermanentDeclined: Boolean,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    onGoToAppSettings: () -> Unit
) {
    AlertDialog(
        icon = {
            Icon(Icons.Filled.Build, contentDescription = "Example Icon")
        },
        title = {
            Text(text = stringResource(id = permissionTextProvider.title))
        },
        text = {
            Text(
                text = stringResource(
                    id = permissionTextProvider.getDescription(
                        isPermanentDeclined
                    )
                )
            )
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (isPermanentDeclined) {
                        onGoToAppSettings()
                    } else {
                        onConfirmation()
                    }
                }
            ) {
                Text(
                    text = if (isPermanentDeclined) {
                        "Grant Permission"
                    } else {
                        "Yes"
                    }
                )
            }
        }
    )
}

@Preview
@Composable
fun PermissionDialogPreview(){

}

interface PermissionTextProvider {
    val title: Int
    fun getDescription(isPermanentDeclined: Boolean): Int
}

class PostNotificationsPermissionTextProvider : PermissionTextProvider {
    override val title: Int = R.string.app_name
    override fun getDescription(isPermanentDeclined: Boolean): Int {
        return if (isPermanentDeclined) {
            R.string.app_name
        } else {
            R.string.app_name
        }
    }
}

class ManageExternalStoragePermissionTextProvider : PermissionTextProvider {
    override val title: Int = R.string.app_name
    override fun getDescription(isPermanentDeclined: Boolean): Int {
        return if (isPermanentDeclined) {
            R.string.app_name
        } else {
            R.string.app_name
        }
    }
}

class ReadExternalStoragePermissionTextProvider : PermissionTextProvider {
    override val title: Int = R.string.app_name
    override fun getDescription(isPermanentDeclined: Boolean): Int {
        return if (isPermanentDeclined) {
            R.string.app_name
        } else {
            R.string.app_name
        }
    }
}

class WriteExternalStoragePermissionTextProvider : PermissionTextProvider {
    override val title: Int = R.string.app_name
    override fun getDescription(isPermanentDeclined: Boolean): Int {
        return if (isPermanentDeclined) {
            R.string.app_name
        } else {
            R.string.app_name
        }
    }
}