package com.purwasadr.pantaucovid.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "covid_data")
data class CovidRateEntity(
    @PrimaryKey
    val id: Long = 0,
    val dataJumlahOdp: Long? = null,
    val dataJumlahPdp: Long? = null,
    val dataTotalSpesimen: Long? = null,
    val dataTotalSpesimenNegatif: Long? = null,
    val penambahanCreated: String? = null,
    val penambahanJumlahMeninggal: Long? = null,
    val penambahanTanggal: String? = null,
    val penambahanJumlahSembuh: Long? = null,
    val penambahanJumlahPositif: Long? = null,
    val penambahanJumlahDirawat: Long? = null,
    val totalJumlahMeninggal: Long? = null,
    val totalJumlahSembuh: Long? = null,
    val totalJumlahPositif: Long? = null,
    val totalJumlahDirawat: Long? = null
)
