package vendyix.musical.vendyixsoundlink.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = SignalAmber,
    secondary = StudioPurple,
    background = DarkBackground,
    surface = DarkSurface,
    onPrimary = Ink,
    onBackground = DarkOnSurface,
    onSurface = DarkOnSurface,
    outline = MutedInk,
)

private val LightColorScheme = lightColorScheme(
    primary = StudioPurple,
    secondary = SignalAmber,
    tertiary = SuccessGreen,
    background = AppBackground,
    surface = AppSurface,
    onPrimary = AppSurface,
    onSecondary = Ink,
    onBackground = Ink,
    onSurface = Ink,
    onSurfaceVariant = MutedInk,
    outline = SoftBorder,
    surfaceVariant = ChipLavender,
)

@Composable
fun ProductAppUDLXJTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = AppTypography,
        content = content,
    )
}
