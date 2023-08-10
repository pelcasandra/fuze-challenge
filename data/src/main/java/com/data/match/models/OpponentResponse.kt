package com.data.match.models

import android.os.Parcelable
import com.domain.match.models.Opponent
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class OpponentResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String? = "",
    @SerializedName("image_url") val image: String? = ""
) : Parcelable {

    fun map() = Opponent(
        id = id,
        name = name ?: "",
        image = image ?: ""
    )
}
