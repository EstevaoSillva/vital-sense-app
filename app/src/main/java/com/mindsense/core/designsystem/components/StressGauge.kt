package com.mindsense.core.designsystem.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mindsense.core.designsystem.theme.MetricTextStyle
import com.mindsense.core.designsystem.theme.MindSenseThemeTokens

@Composable
fun StressGauge(
    score: Int,
    modifier: Modifier = Modifier,
    size: Dp = 180.dp,
    maxScore: Int = 100,
) {
    val progress = (score.toFloat() / maxScore).coerceIn(0f, 1f)
    val animated = animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = 900),
        label = "gauge",
    )
    val color = when {
        progress <= 0.4f -> MindSenseThemeTokens.extendedColors.success
        progress <= 0.7f -> MaterialTheme.colorScheme.primary
        else -> MaterialTheme.colorScheme.error
    }
    val label = when {
        progress <= 0.3f -> "Excelente"
        progress <= 0.5f -> "Normal"
        progress <= 0.7f -> "Elevado"
        else -> "Alto"
    }
    val gaugeTrackColor = MindSenseThemeTokens.extendedColors.gaugeTrack

    Box(
        modifier = modifier.size(size),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val strokeWidth = this.size.minDimension * 0.07f
            val diameter = this.size.minDimension - strokeWidth
            val arcSize = Size(diameter, diameter)
            val topLeft = Offset(strokeWidth / 2, strokeWidth / 2)

            drawArc(
                color = gaugeTrackColor,
                startAngle = 135f,
                sweepAngle = 270f,
                useCenter = false,
                topLeft = topLeft,
                size = arcSize,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
            )
            drawArc(
                color = color,
                startAngle = 135f,
                sweepAngle = 270f * animated.value,
                useCenter = false,
                topLeft = topLeft,
                size = arcSize,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = score.toString(), style = MetricTextStyle, color = MaterialTheme.colorScheme.onSurface)
            Text(
                text = label.uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}
