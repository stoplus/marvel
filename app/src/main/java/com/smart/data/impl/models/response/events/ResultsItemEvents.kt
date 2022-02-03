package com.smart.data.impl.models.response.events

import com.google.gson.annotations.SerializedName

data class ResultsItemEvents(
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
    @SerializedName("start")
    val start: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("resourceURI")
    val resourceURI: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("characters")
    val characters: Characters,
    @SerializedName("urls")
    val urls: List<UrlsItem>?,
    @SerializedName("series")
    val series: Series,
    @SerializedName("modified")
    val modified: String = "",
    @SerializedName("end")
    val end: String = "",
    @SerializedName("id")
    val id: String = "",
)