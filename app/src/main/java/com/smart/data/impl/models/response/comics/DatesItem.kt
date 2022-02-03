package com.smart.data.impl.models.response.comics

import com.google.gson.annotations.SerializedName

data class DatesItem(
    @SerializedName("date")
    val date: String = "",
    @SerializedName("type")
    val type: String = "",
)