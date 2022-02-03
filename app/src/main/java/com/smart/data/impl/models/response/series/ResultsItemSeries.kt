package com.smart.data.impl.models.response.series

import com.google.gson.annotations.SerializedName

data class ResultsItemSeries(
    @SerializedName("next")
    val next: Next,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail?,
    @SerializedName("stories")
    val stories: Stories,
    @SerializedName("previous")
    val previous: Previous,
    @SerializedName("creators")
    val creators: Creators,
    @SerializedName("comics")
    val comics: Comics,
    @SerializedName("startYear")
    val startYear: String = "",
    @SerializedName("rating")
    val rating: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("resourceURI")
    val resourceURI: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("endYear")
    val endYear: String = "",
    @SerializedName("characters")
    val characters: Characters,
    @SerializedName("urls")
    val urls: List<UrlsItem>?,
    @SerializedName("modified")
    val modified: String = "",
    @SerializedName("id")
    val id: String = "",
    @SerializedName("events")
    val events: Events,
)