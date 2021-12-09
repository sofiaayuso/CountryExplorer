package com.example.countryexplorer.network

import com.example.countryexplorer.database.Country
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://restcountries.com/v3.1/all/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface CountriesApiService {

    @GET
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