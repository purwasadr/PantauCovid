package com.purwasadr.pantaucovid.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "city")
data class CityEntity(
    @PrimaryKey
    val id: String,
    val name: String? = null,

)
