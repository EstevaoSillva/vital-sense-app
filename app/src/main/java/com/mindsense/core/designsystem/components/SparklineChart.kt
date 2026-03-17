package com.mindsense.core.designsystem.components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun SparklineChart(
    points: List<Int>,
    modifier: Modifier = Modifier,
    lineColor: Color,
) {
    if (points.isEmpty()) return

    Canvas(modifier = modifier) {
        val min = points.minOrNull()?.toFloat() ?: 0f
        val max = points.maxOrNull()?.toFloat() ?: 0f
        val range = (max - min).takeIf { it > 0f } ?: 1f
        val stepX = if (points.size > 1) size.width / (points.size - 1) else size.width

        val linePath = Path()
        val fillPath = Path()

        points.forEachIndexed { index, value ->
            val x = stepX * index
            val y = size.height - ((value - min) / range) * size.height
            if (index == 0) {
                linePath.moveTo(x, y)
                fillPath.moveTo(x, size.height)
                fillPath.lineTo(x, y)
            } else {
                linePath.lineTo(x, y)
                fillPath.lineTo(x, y)
            }
        }

        fillPath.lineTo(size.width, size.height)
        fillPath.close()

        drawPath(
            path = fillPath,
            color = lineColor.copy(alpha = 0.18f),
        )
        drawPath(
            path = linePath,
            color = lineColor,
            style = Stroke(width = 4f, cap = StrokeCap.Round),
        )
    }
}
