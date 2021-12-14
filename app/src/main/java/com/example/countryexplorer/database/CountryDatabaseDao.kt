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

    @Query("SELECT * FROM country_table ORDER BY name")
    fun getCountries(): Flow<List<Country>>

    @Query("DELETE FROM country_table")
    suspend fun deleteCountries()

    @Query("SELECT * FROM country_table WHERE name = '${countryName}'") //TODO: Fix this
    suspend fun getCountryByName(countryName: String)
}