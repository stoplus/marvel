package com.smart.data.impl.models.response.comics

import com.google.gson.annotations.SerializedName

data class TextObjectsItem(
    @SerializedName("language")
    val language: String = "",
    @SerializedName("text")
    val text: String = "",
    @SerializedName("type")
    val type: String = "",
)