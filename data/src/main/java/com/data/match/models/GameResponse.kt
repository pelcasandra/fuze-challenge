package com.data.match.models

import android.os.Parcelable
import com.domain.match.models.Game
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("match_id") val matchId: Int
) : Parcelable {
    fun map() = Game(
        id = id,
        matchId = matchId
    )
}
