package com.purwasadr.pantaucovid.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class HospitalsItemResponse(

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("bed_availability")
	val bedAvailability: Int? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("name")
	val name: String? = null,


	@field:SerializedName("info")
	val info: String? = null
)