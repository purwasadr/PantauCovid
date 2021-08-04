package com.purwasadr.pantaucovid.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "province")
data class ProvinceEntity(
    @PrimaryKey
    val id: String,
    val name: String
)
