package com.data.models

import android.os.Parcelable
import com.domain.models.Match
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MatchResponse(
    @SerializedName("id") val id: String,
    @SerializedName("games") val games: List<GameResponse>,
    @SerializedName("league") val league: LeagueResponse,
    @SerializedName("serie") val serie: SerieResponse,
    @SerializedName("opponents") val opponents: List<OpponentResponse>? = emptyList(),
) : Parcelable {

    fun map() = Match(
        id = id,
        games = games.map { it.map() },
        league = league.map(),
        serie = serie.map(),
        opponents = opponents?.map { it.map() }
    )
}
