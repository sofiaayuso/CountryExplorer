package com.example.countryexplorer.singlecountry

import com.example.countryexplorer.database.Country

sealed class SingleCountryViewState {
    data class Loaded(val country: Country): SingleCountryViewState()
    object  Error: SingleCountryViewState()
}