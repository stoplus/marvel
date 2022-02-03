package com.smart.data.impl.models.response.series

import com.google.gson.annotations.SerializedName

data class SeriesDataWrapper(
    @SerializedName("data") val data: Data,
)