package com.purwasadr.pantaucovid.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class UpdateResponse(
    @field:SerializedName("penambahan")
    val penambahan: PenambahanResponse? = null,

    @field:SerializedName("total")
    val total: TotalResponse? = null,
)