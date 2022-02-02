package com.smart.data.impl.models.response.stories

import com.google.gson.annotations.SerializedName

data class ResultsItemStories(
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail,
    @SerializedName("creators")
    val creators: Creators,
    @SerializedName("comics")
    val comics: Comics,
    @SerializedName("description")
    val description: String = "",
    @SerializedName("resourceURI")
    val resourceURI: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("type")
    val type: String = "",
    @SerializedName("characters")
    val characters: Characters,
    @SerializedName("originalissue")
    val originalissue: Originalissue,
    @SerializedName("series")
    val series: Series,
    @SerializedName("modified")
    val modified: String = "",
    @SerializedName("id")
    val id: String = "",
    @SerializedName("events")
    val events: Events,
)