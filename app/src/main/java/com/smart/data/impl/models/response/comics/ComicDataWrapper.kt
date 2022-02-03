package com.smart.data.impl.models.response.comics

import com.google.gson.annotations.SerializedName

data class ComicDataWrapper(
    @SerializedName("data") val data: Data,
)