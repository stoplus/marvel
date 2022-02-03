package com.smart.data.impl.models.response.comics

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("results") val results: List<ResultsItemComics>?,
)