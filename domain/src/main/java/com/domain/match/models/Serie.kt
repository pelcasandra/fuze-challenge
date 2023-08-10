package com.domain.match.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Serie(
    val id: Int,
    val name: String,
    val modifiedAt: Date?
) : Parcelable
