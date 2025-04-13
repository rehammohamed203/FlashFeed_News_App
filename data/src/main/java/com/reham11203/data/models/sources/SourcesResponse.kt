package com.reham11203.data.models.sources

import com.google.gson.annotations.SerializedName

data class SourcesResponse(

    @field:SerializedName("sources")
    val sources: List<SourceDTO?>? = null,

    @field:SerializedName("status")
	val status: String? = null,

    @field:SerializedName("code")
	val code: String? = null,

    @field:SerializedName("message")
	val message: String? = null
)