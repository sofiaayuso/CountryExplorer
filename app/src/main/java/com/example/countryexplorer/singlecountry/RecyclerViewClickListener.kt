package com.example.countryexplorer.singlecountry

import android.view.View


interface RecyclerViewClickListener {
    fun onClick(view: View?, position: Int)
}