package com.example.countryexplorer.network

import com.example.countryexplorer.database.Country
import com.example.countryexplorer.database.RemoteCountry
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface CountriesApiService {

    @GET("all")
    suspend fun getCountries(): List<RemoteCountry>

}
