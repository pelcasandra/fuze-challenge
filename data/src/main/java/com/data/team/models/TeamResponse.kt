package com.data.team.models

import android.os.Parcelable
import com.domain.team.models.Team
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TeamResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("players") val players: List<PlayerResponse>? = emptyList()
) : Parcelable {

    fun map() = Team(
        id = id,
        name = name,
        players = players?.map { it.map() } ?: emptyList()
    )
}
