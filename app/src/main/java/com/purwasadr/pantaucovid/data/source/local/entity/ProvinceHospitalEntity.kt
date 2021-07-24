package com.purwasadr.pantaucovid.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.purwasadr.pantaucovid.model.Province

@Entity(tableName = "province_hospital")
data class ProvinceHospitalEntity(
    @PrimaryKey
    val id: String,
    val name: String?
)

fun List<ProvinceHospitalEntity>.toDomain() =
    this.map {
        Province(it.id, it.name ?: "")
    }

