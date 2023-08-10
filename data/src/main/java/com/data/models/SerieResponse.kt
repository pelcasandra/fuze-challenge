package com.data.models

import android.os.Parcelable
import com.domain.models.Serie
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SerieResponse(
    @SerializedName("id") val id: String? = "",
    @SerializedName("name") val name: String? = ""
) : Parcelable {

    fun map() = Serie(
        id = id ?: "",
        name = name ?: ""
    )
}
