package com.smart.data.impl.models.response.events

import com.google.gson.annotations.SerializedName

data class EventDataWrapper(
    @SerializedName("data") val data: Data,
)