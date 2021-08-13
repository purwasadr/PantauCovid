package com.purwasadr.pantaucovid.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CityItemResponse(

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("name")
    val name: String? = null
)