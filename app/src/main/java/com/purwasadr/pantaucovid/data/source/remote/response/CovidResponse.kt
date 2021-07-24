package com.purwasadr.pantaucovid.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CovidResponse(
    @field:SerializedName("data")
    val data: CovidDataResponse? = null,

    @field:SerializedName("update")
    val update: UpdateResponse? = null
)