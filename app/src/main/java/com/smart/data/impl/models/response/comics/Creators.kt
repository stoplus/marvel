package com.smart.data.impl.models.response.comics

import com.google.gson.annotations.SerializedName

data class Creators(@SerializedName("collectionURI")
                    val collectionURI: String = "",
                    @SerializedName("available")
                    val available: String = "",
                    @SerializedName("returned")
                    val returned: String = "",
                    @SerializedName("items")
                    val items: List<ItemsItem>?)