package com.vrushi.emiaggregator.di

import android.content.Context
import com.vrushi.emiaggregator.core.datastore.DataStoreRepository
import com.vrushi.emiaggregator.core.datastore.DataStoreRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Singleton
    @Provides
    fun provideDataStoreRepository(
        @ApplicationContext appContext: Context
    ): DataStoreRepository = DataStoreRepositoryImpl(appContext)

}