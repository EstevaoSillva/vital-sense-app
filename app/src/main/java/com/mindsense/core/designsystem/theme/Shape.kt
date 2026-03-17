package com.mindsense.core.designsystem.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

val MindSenseShapes = Shapes(
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(24.dp),
)

@Immutable
data class MindSenseCustomShapes(
    val button: Shape = RoundedCornerShape(20.dp),
    val input: Shape = RoundedCornerShape(18.dp),
    val card: Shape = RoundedCornerShape(24.dp),
    val chip: Shape = RoundedCornerShape(999.dp),
    val dialog: Shape = RoundedCornerShape(28.dp),
)

val LocalMindSenseCustomShapes = staticCompositionLocalOf { MindSenseCustomShapes() }
