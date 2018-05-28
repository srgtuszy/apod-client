package com.infullmobile.nasapod.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AstronomyMedia(
        @SerializedName("url") val url: String,
        @SerializedName("description") val description: String,
        @SerializedName("media_type") val mediaType: String
) : Serializable
