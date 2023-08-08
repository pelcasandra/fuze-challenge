package com.ui.base.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import com.ui.base.theme.custom.DarkThemeColors
import com.ui.base.theme.custom.DefaultDimensions
import com.ui.base.theme.custom.Dimensions
import com.ui.base.theme.custom.LightThemeColors
import com.ui.base.theme.custom.FuzeColors

private val LocalColors = staticCompositionLocalOf<FuzeColors> { error("No com.ui.base.theme.getColors provided") }
private val LocalDimens = staticCompositionLocalOf<Dimensions> { error("No com.ui.base.theme.getDimens provided") }

val Colors: FuzeColors
    @Composable
    get() = LocalColors.current

val Dimens: Dimensions
    @Composable
    get() = LocalDimens.current


@Composable
fun FuzeTheme(content: @Composable () -> Unit) {
    val colors = if (isSystemInDarkTheme()) DarkThemeColors else LightThemeColors
    val rememberedColors = remember { colors.copy() }.apply { update(colors) }

    CompositionLocalProvider(
        LocalColors provides rememberedColors,
        LocalDimens provides DefaultDimensions
    ) {
        content.invoke()
    }
}
