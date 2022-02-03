package com.smart.data.impl.models.response.comics

import com.google.gson.annotations.SerializedName

data class ItemsItem(
    @SerializedName("name")
    val name: String = "",
    @SerializedName("resourceURI")
    val resourceURI: String = "",
    @SerializedName("type")
    val type: String = "",
)