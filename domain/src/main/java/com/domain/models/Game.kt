package com.domain.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    val id: Long,
    val matchId: Long,
    val beginAt: String
) : Parcelable
