package com.mindsense.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Background = Color(0xFF000000)
val BackgroundAlt = Color(0xFF080808)
val Surface = Color(0xFF0D0D0D)
val SurfaceAlt = Color(0xFF1A1A1A)
val SurfaceMuted = Color(0xFF141414)
val Foreground = Color(0xFFFAFAFA)
val ForegroundMuted = Color(0xFF999999)
val PrimaryBlue = Color(0xFF2962FF)
val SuccessGreen = Color(0xFF00F58A)
val WarningMagenta = Color(0xFFFF2E8B)
val DangerPink = Color(0xFFFF2E8B)
val CardBorder = Color(0x14FFFFFF)
val SoftWhite = Color(0xCCFFFFFF)
val GaugeTrack = Color(0xFF1F1F1F)

@Immutable
data class MindSenseExtendedColors(
    val success: Color = SuccessGreen,
    val info: Color = PrimaryBlue,
    val warning: Color = WarningMagenta,
    val danger: Color = DangerPink,
    val mutedForeground: Color = ForegroundMuted,
    val softWhite: Color = SoftWhite,
    val cardBorder: Color = CardBorder,
    val gaugeTrack: Color = GaugeTrack,
    val brandGradient: Brush = Brush.linearGradient(
        colors = listOf(PrimaryBlue, WarningMagenta),
    ),
    val successGradient: Brush = Brush.linearGradient(
        colors = listOf(SuccessGreen, Color(0xFF00A65A)),
    ),
)

val LocalMindSenseExtendedColors = staticCompositionLocalOf { MindSenseExtendedColors() }
