package com.data.models

import android.os.Parcelable
import com.domain.models.Opponent
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class OpponentResponse(
    @SerializedName("id") val id: String? = "",
    @SerializedName("name") val name: String? = "",
    @SerializedName("image_url") val image: String? = ""
) : Parcelable {

    fun map() = Opponent(
        id = id ?: "",
        name = name ?: "",
        image = image ?: ""
    )
}
