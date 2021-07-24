package com.purwasadr.pantaucovid.data.source.remote.response

import com.google.gson.annotations.SerializedName
import com.purwasadr.pantaucovid.data.source.local.entity.CovidEntity

data class CovidResponse(
    @field:SerializedName("data")
    val data: CovidDataResponse? = null,

    @field:SerializedName("update")
    val update: UpdateResponse? = null
)

fun CovidResponse.toEntity() =
    CovidEntity(
        jumlahPositif = this.update?.total?.jumlahPositif,
        jumlahSembuh = this.update?.total?.jumlahSembuh,
        jumlahMeninggal = this.update?.total?.jumlahMeninggal,
        jumlahOdp = this.data?.jumlahOdp,
        jumlahPdp = this.data?.jumlahPdp,
        id = 1
    )