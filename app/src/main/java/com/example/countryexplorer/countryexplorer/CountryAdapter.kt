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

class CountryAdapter(val listener: RecyclerViewClickListener): ListAdapter<Country, CountryAdapter.ViewHolder>(DIFF) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(listener, parent)
    }

    class ViewHolder private constructor(val listener: RecyclerViewClickListener, itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val countryFlag: ImageView = itemView.findViewById(R.id.flag_url)
        private val countryName: TextView = itemView.findViewById(R.id.country_name)
        private val countryPopulation: TextView = itemView.findViewById(R.id.country_population)

        fun bind(country: Country) {
            countryFlag.load(country.flag)
            countryName.text = country.name
            countryPopulation.text = "Population: " + String.format("%,d", country.population)
            itemView.setOnClickListener { listener.onClick(it, absoluteAdapterPosition) }
        }

        companion object {
            fun from(listener: RecyclerViewClickListener, parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.list_item_country, parent, false)

                return ViewHolder(listener, view)
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
