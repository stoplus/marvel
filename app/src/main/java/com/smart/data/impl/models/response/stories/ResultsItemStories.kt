package com.smart.data.impl.models.response.stories

import com.google.gson.annotations.SerializedName

data class ResultsItemStories(
    @SerializedName("title") val title: String = "",
    @SerializedName("id") val id: String = "",
)