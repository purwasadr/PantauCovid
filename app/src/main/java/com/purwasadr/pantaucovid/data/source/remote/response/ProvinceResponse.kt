package com.purwasadr.pantaucovid.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ProvinceResponse(

	@field:SerializedName("provinces")
	val provinces: List<ProvincesItemResponse>? = null
)