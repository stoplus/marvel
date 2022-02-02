package com.smart.data.impl.models.response.comics

import com.google.gson.annotations.SerializedName

data class ComicDataWrapper(
    @SerializedName("copyright")
    val copyright: String = "",
    @SerializedName("code")
    val code: String = "",
    @SerializedName("data")
    val data: Data,
    @SerializedName("attributionHTML")
    val attributionHTML: String = "",
    @SerializedName("attributionText")
    val attributionText: String = "",
    @SerializedName("etag")
    val etag: String = "",
    @SerializedName("status")
    val status: String = "",
)