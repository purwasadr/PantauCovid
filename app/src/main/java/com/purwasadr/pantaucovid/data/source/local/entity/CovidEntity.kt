package com.purwasadr.pantaucovid.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.purwasadr.pantaucovid.model.Covid

@Entity(tableName = "covid")
data class CovidEntity(
    @ColumnInfo(name ="jumlah_positif")
    val jumlahPositif: Int? = null,
    @ColumnInfo(name ="jumlah_sembuh")
    val jumlahSembuh: Int? = null,
    @ColumnInfo(name ="jumlah_meninggal")
    val jumlahMeninggal: Int? = null,

    @ColumnInfo(name = "jumlah_odp")
    val jumlahOdp: Int?,

    @ColumnInfo(name = "jumlah_pdp")
    val jumlahPdp: Int?,
    @PrimaryKey
    val id: Int = 1,
)

fun CovidEntity.toDomain() =
    Covid(
        jumlahPositif = this.jumlahPositif,
        jumlahSembuh = this.jumlahSembuh,
        jumlahMeninggal = this.jumlahMeninggal,
        jumlahOdp = this.jumlahOdp,
        jumlahPdp = this.jumlahPdp
    )