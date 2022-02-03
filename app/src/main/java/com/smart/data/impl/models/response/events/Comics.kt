package com.smart.data.impl.models.response.events

import com.google.gson.annotations.SerializedName

data class Comics(
    @SerializedName("collectionURI")
    val collectionURI: String = "",
    @SerializedName("available")
    val available: String = "",
    @SerializedName("returned")
    val returned: String = "",
    @SerializedName("items")
    val items: List<ItemsItem>?,
)