package com.example.countryexplorer.singlecountry

import androidx.lifecycle.ViewModel
import com.example.countryexplorer.countryexplorer.CountryExplorerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class SingleCountryViewModel(

    private val repository: CountryExplorerRepository): ViewModel() {

    private val _singleCountryViewStateFlowUpdates: MutableStateFlow<SingleCountryViewState> = MutableStateFlow(
        SingleCountryViewState.Error)
    val countryExplorerViewStateFlow: Flow<SingleCountryViewState> = _singleCountryViewStateFlowUpdates


    suspend fun init {
        getCountryByName()
    }
//
//    private fun observeSingleCountryData() {
//        repository.getSingleCountry()
//            .onStart { country ->
//                handleSingleCountryData(country)
//            }.launchIn(viewModelScope)
//    }
//
//    private fun handleSingleCountryData(country: Country) {
//        if (country) { // TODO: Figure out how I can check if I can load the country data
//            _singleCountryViewStateFlowUpdates.value = SingleCountryViewState.Loaded(country)
//        } else {
//            _singleCountryViewStateFlowUpdates.value = SingleCountryViewState.Error
//        }
//    }
//
    private suspend fun getCountryByName (countryName: String) {
        repository.getCountryByName(countryName)
    }

}

