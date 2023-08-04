import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import com.core.theme.custom.*
import com.ui.theme.custom.DarkThemeColors
import com.ui.theme.custom.LightThemeColors
import com.ui.theme.custom.FuzeColors

private val LocalColors = staticCompositionLocalOf<FuzeColors> { error("No Colors provided") }
private val LocalDimens = staticCompositionLocalOf<Dimensions> { error("No Dimens provided") }

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
