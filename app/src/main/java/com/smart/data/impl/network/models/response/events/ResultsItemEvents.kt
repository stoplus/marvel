package com.smart.data.impl.network.models.response.events

import com.google.gson.annotations.SerializedName

data class ResultsItemEvents(
    @SerializedName("thumbnail") val thumbnail: Thumbnail?,
    @SerializedName("title") val title: String = "",
    @SerializedName("id") val id: String = "",
)
