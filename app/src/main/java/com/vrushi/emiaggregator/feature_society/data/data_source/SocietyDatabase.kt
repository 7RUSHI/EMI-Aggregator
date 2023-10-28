package com.vrushi.emiaggregator.feature_society.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase

/*
TODO: Uncomment and add entities here
@Database(
    entities = [],
    version = 1
)*/
abstract class SocietyDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME="society_db"
    }
}