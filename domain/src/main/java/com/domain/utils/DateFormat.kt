package com.domain.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun toDate(value: String, pattern: String): Date? {
    return try {
        SimpleDateFormat(pattern, Locale.getDefault()).parse(value)
    } catch (formatException: ParseException) {
        null
    }
}

fun Date.getDayOfWeek(): String {
    return SimpleDateFormat("dd/MM HH:mm", Locale.getDefault()).format(this)
}