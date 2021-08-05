package com.purwasadr.pantaucovid.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CovidDataResponse(

    @field:SerializedName("jumlah_odp")
    val jumlahOdp: Long? = null,

    @field:SerializedName("jumlah_pdp")
    val jumlahPdp: Long? = null,

    @field:SerializedName("total_spesimen")
    val totalSpesimen: Long? = null,

    @field:SerializedName("total_spesimen_negatif")
    val totalSpesimenNegatif: Long? = null,

    @field:SerializedName("id")
    val id: Long? = null
)
