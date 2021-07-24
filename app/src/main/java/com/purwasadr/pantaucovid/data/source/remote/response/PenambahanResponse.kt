package com.purwasadr.pantaucovid.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PenambahanResponse(

    @field:SerializedName("created")
    val created: String? = null,

    @field:SerializedName("jumlah_meninggal")
    val jumlahMeninggal: Int? = null,

    @field:SerializedName("tanggal")
    val tanggal: String? = null,

    @field:SerializedName("jumlah_sembuh")
    val jumlahSembuh: Int? = null,

    @field:SerializedName("jumlah_positif")
    val jumlahPositif: Int? = null,

    @field:SerializedName("jumlah_dirawat")
    val jumlahDirawat: Int? = null
)
