package com.vrushi.emiaggregator.core.lib

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.vrushi.emiaggregator.R

class AppNotificationManager private constructor(private val context: Context) {
    private val notificationManager: NotificationManager
    init {
        val generalChannel = NotificationChannel(
            GENERAL_CHANNEL_ID,
            "General",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        generalChannel.description = "App notifications"

        val databaseChannel = NotificationChannel(
            DATABASE_CHANNEL_ID,
            "Database",
            NotificationManager.IMPORTANCE_HIGH
        )
        databaseChannel.description = "Backup and Restore notifications"

        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannels(listOf(generalChannel, databaseChannel))
    }
    fun showGeneralNotification() {
        val notification = NotificationCompat.Builder(context, GENERAL_CHANNEL_ID)
            .setContentTitle("Test title")
            .setContentText("Test text")
            .setSmallIcon(R.drawable.ic_android_black_24dp)
            .build()
        notificationManager.notify(145,notification)
    }
    companion object {
        const val GENERAL_CHANNEL_ID = "general-channel"
        const val DATABASE_CHANNEL_ID = "database-channel"
        private lateinit var appNotificationManager: AppNotificationManager
        fun initialize(ctx: Context): AppNotificationManager {
            appNotificationManager = AppNotificationManager(ctx)
            return appNotificationManager
        }
        fun getInstance(): AppNotificationManager {
            if(!(Companion::appNotificationManager.isInitialized)) {
                throw Exception("App Notification Manager not initialized. You might forgot to call initialize(ctx: Context)")
            }
            return appNotificationManager
        }
    }
}