package com.smart.data.impl.models.response.events

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("total")
    val total: String = "",
    @SerializedName("offset")
    val offset: String = "",
    @SerializedName("limit")
    val limit: String = "",
    @SerializedName("count")
    val count: String = "",
    @SerializedName("results")
    val results: List<ResultsItemEvents>?,
)