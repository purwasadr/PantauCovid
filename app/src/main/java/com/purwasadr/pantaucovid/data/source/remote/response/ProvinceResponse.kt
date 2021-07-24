package com.purwasadr.pantaucovid.data.source.remote.response

import com.google.gson.annotations.SerializedName
import com.purwasadr.pantaucovid.data.source.local.entity.ProvinceHospitalEntity

data class ProvinceResponse(

	@field:SerializedName("provinces")
	val provinces: List<ProvincesItemResponse>? = null
)

fun ProvinceResponse.toEntity() = this.provinces?.map {
	ProvinceHospitalEntity(it.id, it.name)
}
