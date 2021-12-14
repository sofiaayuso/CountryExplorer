package com.example.countryexplorer.countryexplorer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.countryexplorer.R
import com.example.countryexplorer.database.Country
import com.example.countryexplorer.singlecountry.RecyclerViewClickListener
import kotlinx.coroutines.channels.Channel

class CountryAdapter(): ListAdapter<Country, CountryAdapter.ViewHolder>(DIFF) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView),
        RecyclerViewClickListener {
        private val countryFlag: ImageView = itemView.findViewById(R.id.flag_url)
        private val countryName: TextView = itemView.findViewById(R.id.country_name)
        private val countryPopulation: TextView = itemView.findViewById(R.id.country_population)
        private lateinit var mListener: RecyclerViewClickListener

        fun bind(country: Country) {
            countryFlag.load(country.flag)
            countryName.text = country.name
            // TODO: find better way to get "population" string:
            countryPopulation.text = "Population: " + String.format("%,d", country.population)
        }

        fun onClick(view:View) {
            mListener.onClick(view, absoluteAdapterPosition)
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.list_item_country, parent, false)

                return ViewHolder(view)
            }
        }
    }

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<Country>() {
            override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean =
                oldItem == newItem
        }
    }

}
