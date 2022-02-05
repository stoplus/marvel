package com.smart.data.impl.network.models.response.characters

import com.google.gson.annotations.SerializedName

data class ResultsItemCharacter(
    @SerializedName("thumbnail") val thumbnail: Thumbnail?,
    @SerializedName("name") val name: String = "",
    @SerializedName("description") val description: String = "",
    @SerializedName("id") val id: Int? = null,
)
