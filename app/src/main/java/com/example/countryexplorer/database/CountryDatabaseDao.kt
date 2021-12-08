package com.example.countryexplorer.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * Defines methods for using the Country class with Room.
 */
@Dao
interface CountryDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertMany(countries: List<Country>)

    @Query("SELECT * FROM country_table ORDER BY countryId DESC")
    fun getCountries(): Flow<List<Country>>
}