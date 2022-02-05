package com.smart.data.impl.network.models.response.characters

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("results") val results: List<ResultsItemCharacter>?,
)
