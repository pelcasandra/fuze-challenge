package com.domain.match.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Match(
    val id: Int,
    val beginAt: Date?,
    val games: List<Game>,
    val league: League,
    val serie: Serie,
    val status: Status,
    val opponents: List<Opponent>? = emptyList(),
) : Parcelable


enum class Status {
    NotStarted,
    Running,
    Finished
}