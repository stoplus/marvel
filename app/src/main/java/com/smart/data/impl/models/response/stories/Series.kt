package com.smart.data.impl.models.response.stories

import com.google.gson.annotations.SerializedName

data class Series(
    @SerializedName("collectionURI")
    val collectionURI: String = "",
    @SerializedName("available")
    val available: String = "",
    @SerializedName("returned")
    val returned: String = "",
    @SerializedName("items")
    val items: List<ItemsItem>?,
)