package com.purwasadr.pantaucovid.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CityResponse(

	@field:SerializedName("cities")
	val cities: List<CityItemResponse>? = null
)