package com.smart.data.impl.models.response.series

import com.google.gson.annotations.SerializedName

data class ItemsItem(@SerializedName("name")
                     val name: String = "",
                     @SerializedName("resourceURI")
                     val resourceURI: String = "")