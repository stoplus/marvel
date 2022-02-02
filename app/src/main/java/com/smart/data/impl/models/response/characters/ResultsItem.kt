package com.smart.data.impl.models.response.characters

import com.google.gson.annotations.SerializedName

data class ResultsItem(
    @SerializedName("urls") val urls: List<UrlsItem>?,
    @SerializedName("thumbnail") val thumbnail: Thumbnail,
    @SerializedName("stories") val stories: Stories,
    @SerializedName("series") val series: Series,
    @SerializedName("comics") val comics: Comics,
    @SerializedName("name") val name: String = "",
    @SerializedName("description") val description: String = "",
    @SerializedName("modified") val modified: String = "",
    @SerializedName("id") val id: Int? = null,
    @SerializedName("resourceURI") val resourceURI: String = "",
    @SerializedName("events") val events: Events,
)