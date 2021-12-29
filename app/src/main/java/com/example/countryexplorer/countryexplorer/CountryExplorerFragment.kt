package com.example.countryexplorer.countryexplorer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.countryexplorer.R
import com.example.countryexplorer.database.CountryDatabase
import com.example.countryexplorer.database.CountryDatabaseDao
import com.example.countryexplorer.databinding.FragmentCountryExplorerBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import com.example.countryexplorer.singlecountry.RecyclerViewClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.list_item_country.*
import javax.inject.Inject

@AndroidEntryPoint
class CountryExplorerFragment: Fragment(), RecyclerViewClickListener {

    val viewModel: CountryExplorerViewModel by viewModels()
    private lateinit var ui: FragmentCountryExplorerBinding
    private lateinit var adapter: CountryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // TODO: Do I need to initialize these below:
        ui = DataBindingUtil.inflate(
            inflater, R.layout.fragment_country_explorer, container, false)

        // Give the binding object a reference to the ViewModel
        ui.countryExplorerViewModel = viewModel

        adapter = CountryAdapter(this)

        ui.recyclerView.adapter = adapter

        ui.refreshButton.setOnClickListener { onRefreshClicked() }

        viewModel.countryExplorerViewStateFlow
            .onEach { countryExplorerViewState ->
                onViewStateUpdate(countryExplorerViewState)
            }.launchIn(lifecycleScope)

        return ui.root
    }

    fun onViewStateUpdate(viewState: CountryExplorerViewState) {
        when (viewState) {
            is CountryExplorerViewState.NotFound -> {
                ui.loadingIndicator.isVisible = false
                ui.recyclerView.isVisible = false
                ui.tvNotFound.isVisible = true
            }
            is CountryExplorerViewState.Loading -> {
                ui.loadingIndicator.isVisible = true
                ui.recyclerView.isVisible = false //TODO: How do i make it opaque?
                ui.tvNotFound.isVisible = false
            }
            is CountryExplorerViewState.Error -> {
                ui.loadingIndicator.isVisible = false //TODO: if it goes from notfound state to error, screen is empty. correct this.
                ui.recyclerView.isVisible = true
                ui.tvNotFound.isVisible = false
                Toast.makeText(activity, "Error: Could not complete request", Toast.LENGTH_SHORT).show()
            }
            is CountryExplorerViewState.Loaded -> {
                ui.loadingIndicator.isVisible = false
                ui.recyclerView.isVisible = true
                ui.tvNotFound.isVisible = false

                adapter.submitList(viewState.countries)
            }
        }
    }


    fun onRefreshClicked() {
        viewModel.onRefreshClicked()
    }

    // TODO: Fix this
    override fun onClick(view: View?, position: Int) {
        val countryNameTv: TextView = view!!.findViewById(R.id.country_name)
        val countryName = countryNameTv.text.toString()
        val bundle = bundleOf("countryName" to countryName)
        findNavController().navigate(R.id.action_countryExplorerFragment_to_singleCountryFragment, bundle)
    }

}