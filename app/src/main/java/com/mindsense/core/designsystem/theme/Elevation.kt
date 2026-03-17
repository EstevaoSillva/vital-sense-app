package com.mindsense.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class MindSenseElevation(
    val card: Dp = 10.dp,
    val floating: Dp = 16.dp,
)

val LocalMindSenseElevation = staticCompositionLocalOf { MindSenseElevation() }
