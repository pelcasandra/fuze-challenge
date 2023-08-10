package com.domain.team.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.util.Date

@Parcelize
data class Player(
    val id: Long,
    val name: String,
    val nickName: String,
    val image: String,
    val modified: Date?
) : Parcelable
