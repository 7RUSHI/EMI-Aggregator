package com.vrushi.emiaggregator

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.vrushi.emiaggregator.core.presentation.util.sharedViewModel
import com.vrushi.emiaggregator.core.util.AppExtensions.Companion.checkAppPermissionGranted
import com.vrushi.emiaggregator.core.util.AppExtensions.Companion.findActivity
import com.vrushi.emiaggregator.feature_main.presentation.MainScreen
import com.vrushi.emiaggregator.feature_onboard.presentation.AppFlowScreen
import com.vrushi.emiaggregator.feature_onboard.presentation.AppPermissionsScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(startDestination: String, ) {
    val navController = rememberNavController()
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val appViewModel: AppViewModel = hiltViewModel(
        viewModelStoreOwner = context.findActivity() as AppCompatActivity
    )
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        })
    { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
        )
        {
            composable(route = GlobalScreens.PermissionsScreen.route) { entry ->
                val viewModel =
                    entry.sharedViewModel<NavigationViewModel>(navController = navController)
                val state = viewModel.state.collectAsState().value
                val sharedFlow = viewModel.sharedFlow
                AppPermissionsScreen(
                    state = state,
                    snackbarHostState = snackbarHostState,
                    sharedFlow = sharedFlow,
                    onEvent = viewModel::onEvent,
                    onFinished = {
                        navController.navigate(route = AppScreens.AppScreensNavRoot.route) {
                            popUpTo(route = GlobalScreens.PermissionsScreen.route) {
                                inclusive = true
                            }
                        }
                    })
            }
            navigation(
                route = OnBoardingScreens.OnBoardingNavRoot.route,
                startDestination = OnBoardingScreens.AppFlowScreen.route
            ) {
                composable(route = OnBoardingScreens.AppFlowScreen.route) { entry ->
                    val viewModel =
                        entry.sharedViewModel<NavigationViewModel>(navController = navController)
                    AppFlowScreen(onNextScreen = {
                        appViewModel.setASInitialStart(false)
                        if (context.checkAppPermissionGranted()) {
                            navController.navigate(route = AppScreens.AppScreensNavRoot.route) {
                                popUpTo(route = OnBoardingScreens.AppFlowScreen.route) {
                                    inclusive = true
                                }
                            }
                        } else {
                            navController.navigate(GlobalScreens.PermissionsScreen.route)
                        }
                    })
                }
            }
            navigation(
                route = AppScreens.AppScreensNavRoot.route,
                startDestination = AppScreens.MainScreen.route
            ) {
                composable(route = AppScreens.MainScreen.route) { entry ->
                    MainScreen()
                }
            }
        }
    }
}