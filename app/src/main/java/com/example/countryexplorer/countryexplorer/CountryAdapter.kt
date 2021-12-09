package com.example.countryexplorer.countryexplorer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.countryexplorer.R
import com.example.countryexplorer.database.Country

class CountryAdapter(): ListAdapter<Country, CountryAdapter.ViewHolder>(DIFF) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val countryFlag: TextView = itemView.findViewById(R.id.flag_unicode)
        val countryName: TextView = itemView.findViewById(R.id.country_name)
        val countryPopulation: TextView = itemView.findViewById(R.id.country_population)

        fun bind(item: Country) {
//            val res = itemView.context.resources
            countryFlag.text = item.flag
            countryName.text = item.name
            countryPopulation.text =
                item.population.toString() // TODO: might need a function to convert the Long population to an integer?
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
