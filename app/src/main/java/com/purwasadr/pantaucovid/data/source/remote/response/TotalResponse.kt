package com.purwasadr.pantaucovid.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TotalResponse(
    @field:SerializedName("jumlah_meninggal")
    val jumlahMeninggal: Long? = null,

    @field:SerializedName("jumlah_sembuh")
    val jumlahSembuh: Long? = null,

    @field:SerializedName("jumlah_positif")
    val jumlahPositif: Long? = null,

    @field:SerializedName("jumlah_dirawat")
    val jumlahDirawat: Long? = null
)
