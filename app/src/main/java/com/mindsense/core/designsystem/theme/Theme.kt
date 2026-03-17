package com.mindsense.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

private val MindSenseDarkColors = darkColorScheme(
    primary = PrimaryBlue,
    secondary = SurfaceAlt,
    tertiary = SuccessGreen,
    background = Background,
    surface = Surface,
    surfaceBright = SurfaceAlt,
    surfaceVariant = SurfaceMuted,
    onPrimary = Foreground,
    onSecondary = Foreground,
    onBackground = Foreground,
    onSurface = Foreground,
    onSurfaceVariant = ForegroundMuted,
    outline = CardBorder,
    error = WarningMagenta,
    onError = Foreground,
)

@Composable
fun MindSenseTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val scheme = MindSenseDarkColors
    CompositionLocalProvider(
        LocalMindSenseSpacing provides MindSenseSpacing(),
        LocalMindSenseElevation provides MindSenseElevation(),
        LocalMindSenseCustomShapes provides MindSenseCustomShapes(),
        LocalMindSenseExtendedColors provides MindSenseExtendedColors(),
    ) {
        MaterialTheme(
            colorScheme = scheme,
            typography = MindSenseTypography,
            shapes = MindSenseShapes,
            content = content,
        )
    }
}

object MindSenseThemeTokens {
    val spacing: MindSenseSpacing
        @Composable get() = LocalMindSenseSpacing.current

    val elevation: MindSenseElevation
        @Composable get() = LocalMindSenseElevation.current

    val shapes: MindSenseCustomShapes
        @Composable get() = LocalMindSenseCustomShapes.current

    val extendedColors: MindSenseExtendedColors
        @Composable get() = LocalMindSenseExtendedColors.current
}
