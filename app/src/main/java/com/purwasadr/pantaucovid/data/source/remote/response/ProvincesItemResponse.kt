package com.purwasadr.pantaucovid.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ProvincesItemResponse(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String
)