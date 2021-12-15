package com.example.countryexplorer.countryexplorer

import com.example.countryexplorer.database.Country
import com.example.countryexplorer.database.CountryDatabaseDao
import com.example.countryexplorer.network.CountriesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow

interface CountryExplorerRepository {

    suspend fun fetchCountries()

    fun getCountries(): Flow<List<Country>>

    suspend fun getCountryByName(countryName: String): Country
}

class CountryExplorerRepositoryImpl(private val dao: CountryDatabaseDao): CountryExplorerRepository {

    override suspend fun fetchCountries() {

        val countries = CountriesApi.retrofitService.getCountries().map { it.toCountry() }
        delay( 1000 )
        dao.upsertMany(countries)
    }

    override fun getCountries(): Flow<List<Country>> {
        return dao.getCountries()
    }

    override suspend fun getCountryByName(countryName: String): Country {
        return dao.getCountryByName(countryName)
    }
}