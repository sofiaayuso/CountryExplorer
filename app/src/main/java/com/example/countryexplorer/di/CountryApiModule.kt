package com.example.countryexplorer.di

import com.example.countryexplorer.network.CountriesApiService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object CountryApiModule {

    @Provides
    fun retrofitInstance(): Retrofit {
        val moshi = Moshi.Builder().build()
        val BASE_URL = "https://restcountries.com/v3.1/"
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    fun provideCountryService(retrofit: Retrofit): CountriesApiService  {
        return retrofit.create(CountriesApiService::class.java)
    }

}