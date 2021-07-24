package com.purwasadr.pantaucovid.data.source.remote.response

import com.google.gson.annotations.SerializedName
import com.purwasadr.pantaucovid.data.source.local.entity.HospitalEntity

data class HospitalResponse(

	@field:SerializedName("hospitals")
	val hospitals: List<HospitalsItemResponse>? = null,
)

fun HospitalResponse.toEntity() =
	this.hospitals?.map {
		HospitalEntity(
			id = it.id,
			address = it.address,
			bedAvailability = it.bedAvailability,
			phone = it.phone,
			name = it.name,
			info = it.info
		)
	}