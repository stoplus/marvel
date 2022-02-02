package com.smart.data.impl.models.response.stories

import com.google.gson.annotations.SerializedName

data class Originalissue(@SerializedName("name")
                         val name: String = "",
                         @SerializedName("resourceURI")
                         val resourceURI: String = "")