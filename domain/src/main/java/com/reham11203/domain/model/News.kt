package com.reham11203.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.Date

@Parcelize
data class News(
    val title: String? = null,
    val author: String? = null,
    val publishedAt: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val content: String? = null,
    val description: String? = null,

    ) : Parcelable {
    fun getPublishedAtInMillis(): Long? {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val dateTime: Date? = publishedAt?.let { simpleDateFormat.parse(it) }
        return dateTime?.time
    }
}
