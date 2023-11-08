package com.vrushi.emiaggregator

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vrushi.emiaggregator.feature_society.presentation.SocietyScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(vm: NavigationViewModel = viewModel()) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route)
    {
        composable(route = Screen.MainScreen.route) {
            SocietyScreen(navController = navController)
        }
    }
}