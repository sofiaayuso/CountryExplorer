package com.example.countryexplorer.countryexplorer

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.countryexplorer.database.CountryDatabaseDao
import java.lang.IllegalArgumentException

/**
 * Provides the CountryDatabaseDao and context to the ViewModel.
 */
class CountryExplorerViewModelFactory(
    private val repository: CountryExplorerRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountryExplorerViewModel::class.java)) {
            return CountryExplorerViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}