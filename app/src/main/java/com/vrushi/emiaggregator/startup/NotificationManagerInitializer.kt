package com.vrushi.emiaggregator.startup

import android.content.Context
import androidx.startup.Initializer
import com.vrushi.emiaggregator.core.lib.AppNotificationManager

class NotificationManagerInitializer: Initializer<AppNotificationManager> {
    override fun create(context: Context): AppNotificationManager {
        AppNotificationManager.initialize(context)
        return AppNotificationManager.getInstance()
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}