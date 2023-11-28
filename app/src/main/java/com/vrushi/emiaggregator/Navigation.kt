package com.vrushi.emiaggregator

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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.vrushi.emiaggregator.core.presentation.util.sharedViewModel
import com.vrushi.emiaggregator.core.util.AppExtensions.Companion.checkAppPermissionGranted
import com.vrushi.emiaggregator.feature_onboard.presentation.AppFlowScreen
import com.vrushi.emiaggregator.feature_onboard.presentation.AppPermissionsScreen
import com.vrushi.emiaggregator.feature_society.presentation.societies.SocietyScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        })
    { innerPadding ->
        NavHost(navController = navController, startDestination = OnBoardingScreens.NavRoot.route, modifier = Modifier.padding(innerPadding))
        {
            navigation(
                route = OnBoardingScreens.NavRoot.route,
                startDestination = OnBoardingScreens.AppFlowScreen.route
            ) {
                composable(route = OnBoardingScreens.AppFlowScreen.route) { entry ->
                    val viewModel =
                        entry.sharedViewModel<NavigationViewModel>(navController = navController)
                    AppFlowScreen(onNextScreen = {
                            if (context.checkAppPermissionGranted()) {
                                navController.navigate(route = AppScreens.NavRoot.route) {
                                    popUpTo(route = OnBoardingScreens.NavRoot.route) {
                                        inclusive = true
                                    }
                                }
                            }else {
                                navController.navigate(OnBoardingScreens.AppPermissionsScreen.route)
                            }
                    })
                }
                composable(route = OnBoardingScreens.AppPermissionsScreen.route) { entry ->
                    val viewModel =
                        entry.sharedViewModel<NavigationViewModel>(navController = navController)
                    val state = viewModel.state.collectAsState().value
                    val sharedFlow = viewModel.sharedFlow
                    AppPermissionsScreen(
                        state = state,
                        snackbarHostState = snackbarHostState,
                        sharedFlow = sharedFlow,
                        onEvent = viewModel::onEvent,
                        onOnboardingFinished = {
                            navController.navigate(route = AppScreens.NavRoot.route) {
                                popUpTo(route = OnBoardingScreens.NavRoot.route) {
                                    inclusive = true
                                }
                            }
                        })
                }
            }
            navigation(
                route = AppScreens.NavRoot.route,
                startDestination = AppScreens.MainScreen.route
            ) {
                composable(route = AppScreens.MainScreen.route) { entry ->
                    SocietyScreen(navController = navController)
                }
            }
        }
    }
}