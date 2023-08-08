package com.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class OpponentsResponse(
    @SerializedName("opponent") val opponentResponse: OpponentResponse
) : Parcelable
