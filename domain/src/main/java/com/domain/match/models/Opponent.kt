package com.domain.match.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Opponent(
    val id: Int,
    val name: String,
    val image: String
) : Parcelable
