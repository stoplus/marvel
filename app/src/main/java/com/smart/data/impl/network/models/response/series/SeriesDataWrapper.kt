package com.smart.data.impl.network.models.response.series

import com.google.gson.annotations.SerializedName

data class SeriesDataWrapper(
    @SerializedName("data") val data: Data,
)
