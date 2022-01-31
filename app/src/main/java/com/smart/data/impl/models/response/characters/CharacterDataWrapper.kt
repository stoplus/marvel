package com.smart.data.impl.models.response.characters

import com.google.gson.annotations.SerializedName

data class CharacterDataWrapper(
    @SerializedName("copyright") val copyright: String = "",
    @SerializedName("code") val code: String = "",
    @SerializedName("data") val data: Data,
    @SerializedName("attributionHTML") val attributionHTML: String = "",
    @SerializedName("attributionText") val attributionText: String = "",
    @SerializedName("etag") val eTag: String = "",
    @SerializedName("status") val status: String = "",
)