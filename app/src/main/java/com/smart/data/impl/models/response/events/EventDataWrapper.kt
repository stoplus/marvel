package com.smart.data.impl.models.response.events

import com.google.gson.annotations.SerializedName

data class EventDataWrapper(
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