package com.ui.theme.custom

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color


val blue161621 = Color(0xFF161621)
val blue272639 = Color(0xFF272639)
val black0D0D0D = Color(0xFF0D0D0D)
val black282828 = Color(0xFF282828)
val black444444 = Color(0xFF444444)
val whiteFFFFFF = Color(0xFFFFFFFF)
val grayFAFAFA = Color(0x34FAFAFA)
val grayC4C4C4 = Color(0xFFC4C4C4)
val gray888888 = Color(0xFF888888)
val grayE5E5E9 = Color(0xFFE5E5E9)
val redF42A35 = Color(0xFFF42A35)

val LightThemeColors = FuzeColors(
    primary = blue161621,
    tertiary = whiteFFFFFF,
    background = blue161621,
    backgroundMachCard = blue272639,
    text = black282828,
    unSelectText = gray888888,
    border = grayE5E5E9,
    isDark = false
)

val DarkThemeColors = FuzeColors(
    primary = blue161621,
    tertiary = black0D0D0D,
    background = black282828,
    backgroundMachCard = blue272639,
    text = whiteFFFFFF,
    unSelectText = gray888888,
    border = grayE5E5E9,
    isDark = true
)

@Stable
class FuzeColors(
    primary: Color,
    tertiary: Color,
    background: Color,
    backgroundMachCard: Color,
    text: Color,
    unSelectText: Color,
    border: Color,
    isDark: Boolean
) {
    var primary = mutableStateOf(primary).value
        private set
    var tertiary = mutableStateOf(tertiary).value
        private set
    var background = mutableStateOf(background).value
        private set
    var backgroundInput = mutableStateOf(backgroundMachCard).value
        private set
    var text = mutableStateOf(text).value
        private set
    var unSelectText = mutableStateOf(unSelectText).value
        private set
    var border = mutableStateOf(border).value
        private set
    var isDark = mutableStateOf(isDark).value
        private set

    fun copy(): FuzeColors = FuzeColors(
        primary = primary,
        tertiary = tertiary,
        background = background,
        backgroundMachCard = backgroundInput,
        text = text,
        unSelectText = unSelectText,
        border = border,
        isDark = isDark
    )

    fun update(other: FuzeColors) {
        primary = other.primary
        tertiary = other.tertiary
        background = other.background
        backgroundInput = other.backgroundInput
        text = other.text
        unSelectText = other.unSelectText
        border = other.border
        isDark = other.isDark
    }
}