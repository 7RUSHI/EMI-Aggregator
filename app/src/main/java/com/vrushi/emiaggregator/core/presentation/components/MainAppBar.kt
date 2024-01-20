package com.vrushi.emiaggregator.core.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import com.vrushi.emiaggregator.AppScreens
import com.vrushi.emiaggregator.R
import com.vrushi.emiaggregator.core.presentation.AppBarEvents
import com.vrushi.emiaggregator.core.presentation.AppBarState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppBar(
    currentScreen: AppScreens,
    navController: NavController,
    state: AppBarState,
    onEvent: (AppBarEvents) -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(title = {
        Text(
            text = stringResource(currentScreen.resourceId as Int),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }, modifier = modifier, navigationIcon = {
        if (navController.previousBackStackEntry != null) {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack, contentDescription = "Back"
                )
            }
        }
    }, actions = {
        when (currentScreen) {
            AppScreens.SettingScreen -> {

            }

            AppScreens.SocietyScreen -> {
                IconButton(onClick = { onEvent(AppBarEvents.ToggleMenuDropdown) }) {
                    Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "More")
                }
                DropdownMenu(expanded = state.menuExpanded,
                    onDismissRequest = { onEvent(AppBarEvents.ToggleMenuDropdown) }) {
                    DropdownMenuItem(text = { Text(stringResource(R.string.society_menu_settings)) },
                        onClick = {
                            onEvent(AppBarEvents.ToggleMenuDropdown)
                            navController.navigate(AppScreens.SettingScreen.route)
                        })
                }
            }

            else -> {

            }
        }
    })
}