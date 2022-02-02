package com.smart.data.impl.models.response.stories

import com.google.gson.annotations.SerializedName

data class Thumbnail(@SerializedName("path")
                     val path: String = "",
                     @SerializedName("extension")
                     val extension: String = "")