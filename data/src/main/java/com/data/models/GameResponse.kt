package com.data.models

import android.os.Parcelable
import com.domain.models.Game
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("match_id") val matchId: Long,
    @SerializedName("begin_at") val beginAt: String? = ""
) : Parcelable {

    fun map() = Game(
        id = id,
        matchId = matchId,
        beginAt = beginAt ?: ""
    )
}
