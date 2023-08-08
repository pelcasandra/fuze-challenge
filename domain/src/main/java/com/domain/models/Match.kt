package com.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Match(
    val id: String,
    val games: List<Game>,
    val league: League,
    val serie: Serie,
    val opponents: List<Opponent>? = emptyList(),
) : Parcelable
