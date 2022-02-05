package com.smart.data.impl.network.models.response.characters

import com.google.gson.annotations.SerializedName

data class CharacterDataWrapper(
    @SerializedName("data") val data: Data?,
)
