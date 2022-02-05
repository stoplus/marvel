package com.smart.data.impl.network.models.response.comics

import com.google.gson.annotations.SerializedName

data class ResultsItemComics(
    @SerializedName("title") val title: String = "",
    @SerializedName("id") val id: String = "",
    @SerializedName("thumbnail") val thumbnail: Thumbnail?,
)
