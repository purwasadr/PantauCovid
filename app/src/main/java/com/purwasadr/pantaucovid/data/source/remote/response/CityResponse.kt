package com.purwasadr.pantaucovid.data.source.remote.response

import com.google.gson.annotations.SerializedName
import com.purwasadr.pantaucovid.data.source.local.entity.CityEntity

data class CityResponse(

	@field:SerializedName("cities")
	val cities: List<CitiesItemResponse>? = null
)

fun CityResponse.toEntity() = this.cities?.map {
	CityEntity(it.id, it.name)
}