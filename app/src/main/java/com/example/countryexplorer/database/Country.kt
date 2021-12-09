package com.example.countryexplorer.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "country_table")
data class Country (

    @PrimaryKey
    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "population")
    var population: Int = -1,

    @ColumnInfo(name = "flag")
    var flag: String = "" // unicode string
)