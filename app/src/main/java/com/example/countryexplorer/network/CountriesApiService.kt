package com.example.countryexplorer.network

import com.example.countryexplorer.database.Country
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://restcountries.com/v2/"

var moshi = Moshi.Builder().build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CountriesApiService {

    @GET("all")
    suspend fun getCountries(): List<Country>

//    @GET("name/{name}")
//    suspend fun getCountryName(): String
//
//    @GET("all")
//    suspend fun getCountryFlag(): String
//
//    @GET("all")
//    suspend fun getCountryPopulation(): Int
}

object CountriesApi {
    val retrofitService : CountriesApiService by lazy {
        retrofit.create(CountriesApiService::class.java)
    }
}