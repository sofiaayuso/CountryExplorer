package com.example.countryexplorer.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "country_table")
data class Country (

    @PrimaryKey
//    @Embedded
    var name: String = "",

    @ColumnInfo(name = "population")
    var population: Int = -1,

    @ColumnInfo(name = "flag")
    var flag: String = "" // unicode string
)

//data class Name (
//    @ColumnInfo(name = "common")
//    var common: String = ""
//)