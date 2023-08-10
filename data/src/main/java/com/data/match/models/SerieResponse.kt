package com.data.match.models

import android.os.Parcelable
import com.domain.match.models.Serie
import com.domain.utils.toDate
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SerieResponse(
    @SerializedName("id") val id: Int? = 0,
    @SerializedName("name") val name: String? = ""
) : Parcelable {

    fun map() = Serie(
        id = id ?: 0,
        name = name ?: ""
    )
}
