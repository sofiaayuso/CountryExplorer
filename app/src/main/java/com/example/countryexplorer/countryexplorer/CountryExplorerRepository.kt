package com.example.countryexplorer.countryexplorer

import com.example.countryexplorer.database.Country
import com.example.countryexplorer.database.CountryDatabaseDao
import com.example.countryexplorer.network.CountriesApiService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface CountryExplorerRepository {

    suspend fun fetchCountries()

    fun getCountries(): Flow<List<Country>>

    //TODO: I deleted suspend from the function below. Should I put it back?
//    fun getCountryByName(countryName: String): Country
}

class CountryExplorerRepositoryImpl @Inject constructor (
    private val dao: CountryDatabaseDao,
    private val countryExplorerService: CountriesApiService
    ) : CountryExplorerRepository {

    override suspend fun fetchCountries() {

        val countries = countryExplorerService.getCountries().map { it.toCountry() }
        delay( 1000 )
        dao.upsertMany(countries)
    }

    override fun getCountries(): Flow<List<Country>> {
        return dao.getCountries()
    }

//    //TODO: Also here:
//    override fun getCountryByName(countryName: String): Country {
//        return dao.getCountryByName(countryName)
//    }
}