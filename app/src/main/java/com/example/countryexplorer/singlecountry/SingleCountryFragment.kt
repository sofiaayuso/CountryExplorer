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
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.countryexplorer.R
import com.example.countryexplorer.countryexplorer.CountryExplorerRepository
import com.example.countryexplorer.countryexplorer.CountryExplorerRepositoryImpl
import com.example.countryexplorer.database.CountryDatabase
import com.example.countryexplorer.database.CountryDatabaseDao
import com.example.countryexplorer.databinding.FragmentSingleCountryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.list_item_country.*
import org.w3c.dom.Text
import javax.inject.Inject

@AndroidEntryPoint
class SingleCountryFragment() : Fragment() {

    private val viewModel: SingleCountryViewModel by viewModels()
    private lateinit var ui: FragmentSingleCountryBinding
    val args: SingleCountryFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        ui = DataBindingUtil.inflate(inflater, R.layout.fragment_single_country, container, false)

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
//        viewModel.getCountryByName(countryName)
    }

}

