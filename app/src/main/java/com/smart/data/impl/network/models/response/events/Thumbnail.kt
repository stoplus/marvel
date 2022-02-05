package com.smart.data.impl.network.models.response.events

import com.google.gson.annotations.SerializedName

data class Thumbnail(
    @SerializedName("path") val path: String = "",
    @SerializedName("extension") val extension: String = "",
)
