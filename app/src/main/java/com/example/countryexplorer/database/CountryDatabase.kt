package com.example.countryexplorer.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Database(entities = [Country::class], version = 4, exportSchema = false)
abstract class CountryDatabase : RoomDatabase() {

    /**
     * Connects database to DAO
     */
    abstract val countryDatabaseDao: CountryDatabaseDao


}