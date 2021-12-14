package com.example.countryexplorer.countryexplorer

import com.example.countryexplorer.database.Country
import com.example.countryexplorer.database.CountryDatabaseDao
import com.example.countryexplorer.network.CountriesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow

interface CountryExplorerRepository {

    suspend fun fetchCountries()

    fun getCountries(): Flow<List<Country>>

    fun fetchCountryByName(): Flow<Country>
}

class CountryExplorerRepositoryImpl(private val dao: CountryDatabaseDao): CountryExplorerRepository {

    override suspend fun fetchCountries() {
//        val country1 = Country(name="Country1", population = 1 )
//        val country2 = Country(name="Country2", population = 2 )
//        val country3 = Country(name="Country3", population = 3 )
//        val country4 = Country(name="Country4", population = 4 )
//        val country5 = Country(name="Country5", population = 5 )
//        val countries = listOf(country5, country2, country3, country4, country1)

        val countries = CountriesApi.retrofitService.getCountries().map { it.toCountry() }
        delay( 1000 )
        dao.upsertMany(countries)

    }

    override fun getCountries(): Flow<List<Country>> {
        return dao.getCountries()
    }

    override fun fetchCountryByName(countryName: String): Flow<Country> {
        return dao.getCountryByName(countryName)
    }
}