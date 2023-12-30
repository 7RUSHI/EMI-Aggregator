package com.vrushi.emiaggregator

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vrushi.emiaggregator.core.util.AppExtensions.Companion.appSettingsDataStore
import com.vrushi.emiaggregator.core.util.AppExtensions.Companion.checkAppPermissionGranted
import com.vrushi.emiaggregator.ui.theme.EMIAggregatorTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var startScreen: String = AppScreens.NavRoot.route
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            viewModel.isLoading.value
        }
        runBlocking {
            val appSettings = appSettingsDataStore.data.first()
            if (appSettings.initialStartUp) {
                startScreen = OnBoardingScreens.NavRoot.route
            } else {
                if (!applicationContext.checkAppPermissionGranted()) {
                    startScreen = GlobalScreens.PermissionsScreen.route
                }
            }
        }
        setContent {
            val appSettingsState = viewModel.appSettingsState.collectAsStateWithLifecycle()
            EMIAggregatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Navigation(startDestination = startScreen)
                }
            }
            //AppNotificationManager.getInstance().showGeneralNotification()
        }
    }
}
