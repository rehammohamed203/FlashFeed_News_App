package com.reham11203.data.models.news

import com.google.gson.annotations.SerializedName
import com.reham11203.data.models.sources.SourceDTO
import com.reham11203.domain.model.News

data class NewsDTO(

    @field:SerializedName("publishedAt")
    val publishedAt: String? = null,

    @field:SerializedName("author")
    val author: String? = null,

    @field:SerializedName("urlToImage")
    val urlToImage: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("source")
    val source: SourceDTO? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("content")
    val content: String? = null
) {
    fun toNews() = News(
        title = title,
        author = author,
        publishedAt = publishedAt,
        url = url,
        urlToImage = urlToImage,
        description = description,
        content = content
    )
}