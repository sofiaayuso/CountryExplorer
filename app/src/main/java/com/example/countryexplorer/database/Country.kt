package com.example.countryexplorer.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "country_table")
data class Country (

    @PrimaryKey
    var name: String = "",

    @ColumnInfo(name = "population")
    var population: Int = -1,

    @ColumnInfo(name = "flag")
    var flag: String = "", // URL

    @ColumnInfo(name = "continent")
    var continent: String = ""
)

data class RemoteFlags(

    val png: String,

    val svg: String
)

data class RemoteName(

    val common: String,

    val official: String
)

data class RemoteCountry (

    val name: RemoteName,

    val flags: RemoteFlags,

    val population: Int,

    val continent: String

) {
    fun toCountry(): Country {
        return Country(name.common, population, flags.png, continent)
    }

}
