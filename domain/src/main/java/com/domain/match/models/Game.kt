package com.domain.match.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    val id: Int,
    val matchId: Int,
    val status: Status
) : Parcelable

enum class Status {
    NotStarted,
    Running,
    Finished
}
