package com.vrushi.emiaggregator.feature_society.presentation.societies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vrushi.emiaggregator.AppScreens
import com.vrushi.emiaggregator.R
import com.vrushi.emiaggregator.feature_society.presentation.SocietyViewModel
import com.vrushi.emiaggregator.feature_society.presentation.societies.components.OutlinedCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SocietyScreen(
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    vm: SocietyViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.inversePrimary
                ),
                title = {
                    Text(
                        text = stringResource(id = R.string.society_app_bar_title),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                actions = {
                    IconButton(onClick = { vm.openDropDown() }) {
                        Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Add")
                    }
                    DropdownMenu(
                        expanded = vm.expanded,
                        onDismissRequest = { vm.closeDropDown() }
                    ) {
                        DropdownMenuItem(
                            text = { Text(stringResource(R.string.society_menu_settings)) },
                            onClick = {
                                navController.navigate(AppScreens.SettingScreen.route)
                            }
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            OutlinedCard("Society Screen")
        }
    }

}