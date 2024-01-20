package com.vrushi.emiaggregator

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.vrushi.emiaggregator.core.util.AppExtensions
import com.vrushi.emiaggregator.core.util.AppExtensions.Companion.appSettingsDataStore
import com.vrushi.emiaggregator.core.util.AppExtensions.Companion.checkAppPermissionGranted
import com.vrushi.emiaggregator.ui.theme.EMIAggregatorTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }
    private var startScreen: String = AppScreens.AppScreensNavRoot.route
    private val viewModel: AppViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        runBlocking {
            val appSettings = appSettingsDataStore.data.first()
            if (appSettings.initialStartUp) {
                startScreen = OnBoardingScreens.OnBoardingNavRoot.route
            } else {
                if (!applicationContext.checkAppPermissionGranted()) {
                    startScreen = GlobalScreens.PermissionsScreen.route
                }
            }
            AppCompatDelegate.setApplicationLocales(AppExtensions.getLanguageTag(appSettings.appLanguage))
            AppCompatDelegate.setDefaultNightMode(AppExtensions.getThemeMode(appSettings.appTheme))
        }
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            viewModel.isLoading.value
        }
        setContent {
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
