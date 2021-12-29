package com.example.countryexplorer.di

import android.content.Context
import androidx.room.Room
import com.example.countryexplorer.database.CountryDatabase
import com.example.countryexplorer.database.CountryDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    fun provideDatabaseDao(database: CountryDatabase): CountryDatabaseDao {
        return database.countryDatabaseDao
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CountryDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            CountryDatabase::class.java,
            "country_table"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

}

