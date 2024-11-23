package com.mshetty.demoapp.model

import com.google.gson.annotations.SerializedName

data class CountryResponse(

	@field:SerializedName("CountryResponse")
	val countryResponse: List<CountryResponseItem?>? = null
)

data class CountryResponseItem(

	@field:SerializedName("name")
	val name: Name? = null,

	@field:SerializedName("flags")
	val flags: Flags? = null,

)

data class Name(
	val nativeName: NativeName? = null,
	val common: String? = null,
	val official: String? = null
)

data class NativeName(
	val kor: Kor? = null
)

data class Kor(
	val common: String? = null,
	val official: String? = null
)


data class Flags(

	@field:SerializedName("svg")
	val svg: String? = null,

	@field:SerializedName("png")
	val png: String? = null,

	@field:SerializedName("alt")
	val alt: String? = null
)

