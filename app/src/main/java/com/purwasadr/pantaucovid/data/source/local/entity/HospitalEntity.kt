package com.purwasadr.pantaucovid.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hospital")
data class HospitalEntity(
    @PrimaryKey
    val id: String,
    val address: String? = null,

    @ColumnInfo(name = "bed_availability")
    val bedAvailability: Int? = null,
    val phone: String? = null,
    val name: String? = null,
    val info: String? = null
)
