package com.example.countryexplorer.singlecountry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.countryexplorer.R
import com.example.countryexplorer.countryexplorer.CountryExplorerRepository
import com.example.countryexplorer.countryexplorer.CountryExplorerRepositoryImpl
import com.example.countryexplorer.database.CountryDatabase
import com.example.countryexplorer.database.CountryDatabaseDao
import com.example.countryexplorer.databinding.FragmentSingleCountryBinding
import kotlinx.android.synthetic.main.list_item_country.*
import org.w3c.dom.Text

class SingleCountryFragment() : Fragment() {

    private lateinit var viewModel: SingleCountryViewModel
    private lateinit var viewModelFactory: SingleCountryViewModelFactory
    private lateinit var ui: FragmentSingleCountryBinding
    private lateinit var repository: CountryExplorerRepository
    private lateinit var database: CountryDatabase
    private lateinit var dao: CountryDatabaseDao
    val args: SingleCountryFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        ui = DataBindingUtil.inflate(inflater, R.layout.fragment_single_country, container, false)

        val application = requireNotNull(this.activity).application

        database = CountryDatabase.getInstance(application)
        dao = database.countryDatabaseDao
        repository = CountryExplorerRepositoryImpl(dao)
        viewModelFactory = SingleCountryViewModelFactory(repository)

        viewModel =
            ViewModelProvider(this, viewModelFactory).get(SingleCountryViewModel::class.java)

        ui.singleCountryViewModel = viewModel

        // this logic shouldn't be here
        val countryName = getCountryNameFromArgs()
        getCountryDataFromDb(countryName)


////        // TODO: fix this:
////        viewModel.singleCountryViewStateFlow
////            .onEach {
////                singleCountryViewState
////                onViewStateUpdate(singleCountryViewState)
////            }.launchIn(lifecycleScope)

        return ui.root
    }

    fun onViewStateUpdate(viewState: SingleCountryViewState) {
        when (viewState) {
            is SingleCountryViewState.Loaded -> {
                ui.flagUrl.isVisible = true
                ui.countryName.isVisible = true
                ui.continent.isVisible = true
                ui.countryPopulation.isVisible = true
            }
            is SingleCountryViewState.Error -> {
                ui.flagUrl.isVisible = false
                ui.countryName.isVisible = false
                ui.continent.isVisible = false
                ui.countryPopulation.isVisible = false
                Toast.makeText(activity, "Error: Country data not available", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun getCountryNameFromArgs(): String {
        val tv: TextView = view!!.findViewById(R.id.country_name)
        return arguments?.getString("country_name").toString()
    }

    private fun getCountryDataFromDb(countryName: String) {
        viewModel.getCountryByName(countryName)
    }

}

