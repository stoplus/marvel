package com.smart.data.impl.network.models.response.series

import com.google.gson.annotations.SerializedName

data class ResultsItemSeries(
    @SerializedName("thumbnail") val thumbnail: Thumbnail?,
    @SerializedName("title") val title: String = "",
    @SerializedName("id") val id: String = "",
)
