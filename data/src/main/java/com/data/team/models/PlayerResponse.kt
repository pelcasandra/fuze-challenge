package com.data.team.models

import android.os.Parcelable
import com.domain.utils.toDate
import com.domain.team.models.Player
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlayerResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("first_name") val name: String? = "",
    @SerializedName("name") val nickName: String,
    @SerializedName("image_url") val image: String? = "",
    @SerializedName("modified_at") val modified: String? = ""

) : Parcelable {

    fun map() = Player(
        id = id,
        name = name ?: "",
        nickName = nickName,
        image = image ?: "",
        modified = toDate(modified ?: "", "yyyy-MM-dd'T'HH:mm:ss")
    )
}
