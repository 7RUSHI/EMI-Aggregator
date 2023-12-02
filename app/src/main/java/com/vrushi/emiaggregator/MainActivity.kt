package com.vrushi.emiaggregator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.vrushi.emiaggregator.core.util.AppExtensions.Companion.appSettingsDataStore
import com.vrushi.emiaggregator.core.util.AppExtensions.Companion.checkAppPermissionGranted
import com.vrushi.emiaggregator.ui.theme.EMIAggregatorTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    var destination: String = AppScreens.NavRoot.route
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appSettings = runBlocking {
            applicationContext.appSettingsDataStore.data.first()
        }
        if (appSettings.initialStartUp) {
            destination = OnBoardingScreens.NavRoot.route
        } else {
            if (!applicationContext.checkAppPermissionGranted()) {
                destination = GlobalScreens.AppPermissionsScreen.route
            }
        }
        setContent {
            EMIAggregatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Navigation(startDestination = destination)
                }
            }
            //AppNotificationManager.getInstance().showGeneralNotification()
        }
    }
}
