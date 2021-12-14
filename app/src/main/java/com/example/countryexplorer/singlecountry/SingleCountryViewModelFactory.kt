package com.example.countryexplorer.singlecountry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.countryexplorer.countryexplorer.CountryExplorerRepository
import java.lang.IllegalArgumentException

/**
 * Provides the CountryDatabaseDao and context to the ViewModel.
 */

class SingleCountryViewModelFactory(
    private val repository: CountryExplorerRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SingleCountryViewModel::class.java)) {
            return SingleCountryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}