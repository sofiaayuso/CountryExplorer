package com.example.countryexplorer.countryexplorer

import com.example.countryexplorer.database.Country

sealed class CountryExplorerViewState {
    data class Loaded(val countries: List<Country>) : CountryExplorerViewState()
    object Loading : CountryExplorerViewState()
    object NotFound : CountryExplorerViewState()
}
