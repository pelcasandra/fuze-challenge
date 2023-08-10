package com.data.match.models

import android.os.Parcelable
import com.domain.match.models.Match
import com.domain.match.models.Status
import com.domain.utils.toDate
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MatchResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("begin_at") val beginAt: String? = "",
    @SerializedName("games") val games: List<GameResponse>,
    @SerializedName("league") val league: LeagueResponse,
    @SerializedName("serie") val serie: SerieResponse,
    @SerializedName("status") val status: String,
    @SerializedName("opponents") val opponents: List<OpponentsResponse>? = emptyList(),
) : Parcelable {

    fun map() = Match(
        id = id,
        beginAt = toDate(beginAt ?: "", "yyyy-MM-dd'T'HH:mm:ss"),
        games = games.map { it.map() },
        league = league.map(),
        serie = serie.map(),
        status = when (status) {
            "not_started" -> Status.NotStarted
            "running" -> Status.Running
            else -> Status.Finished
        },
        opponents = opponents?.map { it.opponentResponse.map() }
    )
}
