package com.example.countryexplorer.singlecountry

import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.example.countryexplorer.countryexplorer.CountryExplorerRepository
import com.example.countryexplorer.database.Country
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class SingleCountryViewModel(

    private val repository: CountryExplorerRepository): ViewModel() {

    private val _singleCountryViewStateFlowUpdates: MutableStateFlow<SingleCountryViewState> = MutableStateFlow(
        SingleCountryViewState.Error)
    val countryExplorerViewStateFlow: Flow<SingleCountryViewState> = _singleCountryViewStateFlowUpdates


//    suspend fun init {
//        getCountryByName()
//    }
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
    fun getCountryByName (countryName: String) {
        val country = repository.getCountryByName(countryName)
        fillCountryText(country)
    }

    private fun fillCountryText(country: Country) {
        // do something here to fill information from the country into the text views.
    }


}

