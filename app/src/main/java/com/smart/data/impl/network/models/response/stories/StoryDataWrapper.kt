package com.smart.data.impl.network.models.response.stories

import com.google.gson.annotations.SerializedName

data class StoryDataWrapper(
    @SerializedName("data") val data: Data?,
)
