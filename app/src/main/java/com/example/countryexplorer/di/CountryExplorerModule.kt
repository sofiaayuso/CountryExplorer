package com.example.countryexplorer.di

import com.example.countryexplorer.countryexplorer.CountryExplorerRepository
import com.example.countryexplorer.countryexplorer.CountryExplorerRepositoryImpl
import com.example.countryexplorer.database.CountryDatabaseDao
import com.example.countryexplorer.network.CountriesApiService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class CountryExplorerModule {

    @Binds
    abstract fun bindCountryExplorerRepository(impl: CountryExplorerRepositoryImpl): CountryExplorerRepository

//    @Provides
//    fun providesCountryExplorerRepository (
//        dao: CountryDatabaseDao,
//        countryExplorerService: CountriesApiService
//    ): CountryExplorerRepository {
//
//       return CountryExplorerRepositoryImpl(dao, countryExplorerService)
//    }
}