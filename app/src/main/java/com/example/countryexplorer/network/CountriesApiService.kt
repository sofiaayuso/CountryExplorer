package com.example.countryexplorer.network

import com.example.countryexplorer.database.Country
import com.example.countryexplorer.database.RemoteCountry
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://restcountries.com/v3.1/"

var moshi = Moshi.Builder().build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CountriesApiService {

    @GET("all")
    suspend fun getCountries(): List<RemoteCountry>

}

object CountriesApi {
    val retrofitService : CountriesApiService by lazy {
        retrofit.create(CountriesApiService::class.java)
    }
}