package com.reham11203.flashfeed.api.models.news

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.reham11203.flashfeed.api.models.sources.Source
import java.text.SimpleDateFormat
import java.util.Date

@Parcelize
data class News(

	@field:SerializedName("publishedAt")
	val publishedAt: String? = null,

	@field:SerializedName("author")
	val author: String? = null,

	@field:SerializedName("urlToImage")
	val urlToImage: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("source")
	val source: Source? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("content")
	val content: String? = null
) : Parcelable{
	fun getPublishedAtInMillis() : Long?{
		val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
		val dateTime : Date? = publishedAt?.let{simpleDateFormat.parse(it)}
		return dateTime?.time
	}
}