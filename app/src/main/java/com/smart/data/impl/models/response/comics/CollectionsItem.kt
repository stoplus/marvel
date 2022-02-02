package com.smart.data.impl.models.response.comics

import com.google.gson.annotations.SerializedName

data class CollectionsItem(@SerializedName("name")
                           val name: String = "",
                           @SerializedName("resourceURI")
                           val resourceURI: String = "")