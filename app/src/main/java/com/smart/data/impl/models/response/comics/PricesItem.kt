package com.smart.data.impl.models.response.comics

import com.google.gson.annotations.SerializedName

data class PricesItem(
    @SerializedName("price")
    val price: String = "",
    @SerializedName("type")
    val type: String = "",
)