package com.data.match.models

import android.os.Parcelable
import com.domain.match.models.League
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LeagueResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("image_url") val image: String? = ""
) : Parcelable {

    fun map() = League(
        id = id,
        name = name,
        image = image ?: ""
    )
}
