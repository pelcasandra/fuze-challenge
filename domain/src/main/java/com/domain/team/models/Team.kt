package com.domain.team.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Team(
    val id: Int,
    val name: String,
    val players: List<Player>
) : Parcelable
