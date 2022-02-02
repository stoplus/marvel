package com.smart.data.impl.models.response.series

import com.google.gson.annotations.SerializedName

data class UrlsItem(@SerializedName("type")
                    val type: String = "",
                    @SerializedName("url")
                    val url: String = "")