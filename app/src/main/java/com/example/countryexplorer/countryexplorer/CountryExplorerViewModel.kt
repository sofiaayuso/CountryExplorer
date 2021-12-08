package com.example.countryexplorer.countryexplorer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countryexplorer.database.Country
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
 * ViewModel for CountryExplorerFragment.
 */
class CountryExplorerViewModel(

    val repository: CountryExplorerRepository): ViewModel() {


    private val _countryExplorerViewStateFlowUpdates: MutableStateFlow<CountryExplorerViewState> = MutableStateFlow(CountryExplorerViewState.NotFound)
    val countryExplorerViewStateFlow: Flow<CountryExplorerViewState> = _countryExplorerViewStateFlowUpdates

    init {
        observeCountryData()
    }

    private fun observeCountryData() {
        repository.getCountries()
            .onEach { countries ->
                handleCountryData(countries)
            }.launchIn(viewModelScope)
    }

    fun handleCountryData(countries: List<Country>) {
        if (countries.isNotEmpty()) {
            _countryExplorerViewStateFlowUpdates.value = CountryExplorerViewState.Loaded(countries)
        } else {
            _countryExplorerViewStateFlowUpdates.value = CountryExplorerViewState.NotFound
        }
    }

    fun onRefreshClicked() {
        viewModelScope.launch {
            _countryExplorerViewStateFlowUpdates.value = CountryExplorerViewState.Loading
            repository.fetchCountries()
        }
    }

    /**
    //     * Converted nights to Spanned for displaying.
    //     */
//    val countriesString = Transformations.map(countries) { countries ->
//        formatCountries(countries, application.resources)
//    }   // formatNights is a function in the Util.kt file. Not sure if I need this file in my project

}