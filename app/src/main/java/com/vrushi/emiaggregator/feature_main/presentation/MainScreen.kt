package com.vrushi.emiaggregator.feature_main.presentation

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vrushi.emiaggregator.AppScreens
import com.vrushi.emiaggregator.AppViewModel
import com.vrushi.emiaggregator.core.presentation.components.MainAppBar
import com.vrushi.emiaggregator.core.util.AppExtensions.Companion.findActivity
import com.vrushi.emiaggregator.feature_main.MainViewModel
import com.vrushi.emiaggregator.feature_main.UiMainEvents
import com.vrushi.emiaggregator.feature_settings.presentation.AppSettingsScreen
import com.vrushi.emiaggregator.feature_society.presentation.societies.SocietyScreen
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    vm: MainViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val appViewModel: AppViewModel = hiltViewModel(
        viewModelStoreOwner = context.findActivity() as AppCompatActivity
    )
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = AppScreens.valueOf(
        backStackEntry?.destination?.route ?: AppScreens.SocietyScreen.route
    )
    val appBarState by vm.appBarState.collectAsStateWithLifecycle()
    Scaffold(modifier = Modifier.fillMaxSize(), snackbarHost = {
        SnackbarHost(hostState = snackbarHostState)
    }, topBar = {
        MainAppBar(
            currentScreen = currentScreen,
            navController = navController,
            state = appBarState,
            onEvent = vm::onEvent
        )
    }) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = AppScreens.SocietyScreen.route
        ) {

            composable(route = AppScreens.SocietyScreen.route) {
                SocietyScreen()
            }
            composable(route = AppScreens.SettingScreen.route) { entry ->
                AppSettingsScreen(
                    snackbarHostState = snackbarHostState,
                    appSettingsState = appViewModel.appSettingsState.collectAsStateWithLifecycle()
                )
            }

        }
    }
    LaunchedEffect(key1 = true) {
        vm.sharedFlow.collectLatest { event ->
            when (event) {
                is UiMainEvents.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }
            }
        }
    }

}