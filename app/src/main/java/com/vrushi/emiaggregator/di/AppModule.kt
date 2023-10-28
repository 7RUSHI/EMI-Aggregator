package com.vrushi.emiaggregator.di

import android.app.Application
import androidx.room.Room
import com.vrushi.emiaggregator.feature_society.data.data_source.SocietyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /*
    TODO: Uncomment
    @Provides
    @Singleton
    fun provideSocietyDatabase(app: Application): SocietyDatabase{
        return Room.databaseBuilder(
            app,
            SocietyDatabase::class.java,
            SocietyDatabase.DATABASE_NAME
        ).build()
    }*/
}