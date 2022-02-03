package com.smart.data.impl.models.response.stories

import com.google.gson.annotations.SerializedName

data class StoryDataWrapper(
    @SerializedName("data") val data: Data,
)