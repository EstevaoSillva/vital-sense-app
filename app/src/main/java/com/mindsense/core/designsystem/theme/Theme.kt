package com.mindsense.core.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

private val MindSenseColors = darkColorScheme(
    primary = PrimaryBlue,
    secondary = SurfaceAlt,
    tertiary = SuccessGreen,
    background = Background,
    surface = Surface,
    surfaceVariant = SurfaceAlt,
    onPrimary = Foreground,
    onSecondary = Foreground,
    onBackground = Foreground,
    onSurface = Foreground,
    outline = Border,
    error = WarningMagenta,
)

@Composable
fun MindSenseTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalMindSenseSpacing provides MindSenseSpacing(),
        LocalMindSenseElevation provides MindSenseElevation(),
    ) {
        MaterialTheme(
            colorScheme = MindSenseColors,
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
}
