package com.smart.data.impl.network.models.response.events

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("results") val results: List<ResultsItemEvents>?,
)
