package com.data.match.models

import android.os.Parcelable
import com.domain.match.models.Game
import com.domain.match.models.Status
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("match_id") val matchId: Int,
    @SerializedName("status") val status: String? = ""
) : Parcelable {

    fun map() = Game(
        id = id,
        matchId = matchId,
        status = when (status) {
            "not_started" -> Status.NotStarted
            "running" -> Status.Running
            else -> Status.Finished
        }
    )
}
