package com.purwasadr.pantaucovid.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CovidDataResponse(

    @field:SerializedName("jumlah_odp")
    val jumlahOdp: Int? = null,

    @field:SerializedName("jumlah_pdp")
    val jumlahPdp: Int? = null,

    @field:SerializedName("total_spesimen")
    val totalSpesimen: Int? = null,

    @field:SerializedName("total_spesimen_negatif")
    val totalSpesimenNegatif: Int? = null,

    @field:SerializedName("id")
    val id: Int? = null
)
