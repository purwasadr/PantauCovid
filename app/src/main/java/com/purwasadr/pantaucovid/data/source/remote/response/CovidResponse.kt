package com.purwasadr.pantaucovid.data.source.remote.response

import com.google.gson.annotations.SerializedName
import com.purwasadr.pantaucovid.data.source.local.entity.CovidRateEntity

data class CovidResponse(
    @field:SerializedName("data")
    val data: CovidDataResponse? = null,

    @field:SerializedName("update")
    val update: UpdateResponse? = null
)

fun CovidResponse.toEntity() =
    this.run {
        CovidRateEntity(
            dataJumlahOdp = data?.jumlahOdp,
            dataJumlahPdp = data?.jumlahPdp,
            dataTotalSpesimen = data?.totalSpesimen,
            dataTotalSpesimenNegatif = data?.totalSpesimenNegatif,
            penambahanCreated = update?.penambahan?.created,
            penambahanJumlahMeninggal = update?.penambahan?.jumlahMeninggal,
            penambahanTanggal = update?.penambahan?.tanggal,
            penambahanJumlahSembuh = update?.penambahan?.jumlahSembuh,
            penambahanJumlahPositif = update?.penambahan?.jumlahPositif,
            penambahanJumlahDirawat = update?.penambahan?.jumlahDirawat,
            totalJumlahMeninggal = update?.total?.jumlahMeninggal,
            totalJumlahSembuh = update?.total?.jumlahSembuh,
            totalJumlahPositif = update?.total?.jumlahPositif,
            totalJumlahDirawat = update?.total?.jumlahDirawat,
        )
    }
