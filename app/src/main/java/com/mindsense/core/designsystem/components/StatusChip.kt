package com.mindsense.core.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.mindsense.core.designsystem.theme.MindSenseThemeTokens

enum class StatusChipTone {
    Success,
    Info,
    Warning,
    Neutral,
}

@Composable
fun StatusChip(
    text: String,
    tone: StatusChipTone,
    modifier: Modifier = Modifier,
) {
    val background = when (tone) {
        StatusChipTone.Success -> MindSenseThemeTokens.extendedColors.success.copy(alpha = 0.12f)
        StatusChipTone.Info -> MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)
        StatusChipTone.Warning -> MaterialTheme.colorScheme.error.copy(alpha = 0.12f)
        StatusChipTone.Neutral -> MaterialTheme.colorScheme.surfaceVariant
    }
    val content = when (tone) {
        StatusChipTone.Success -> MindSenseThemeTokens.extendedColors.success
        StatusChipTone.Info -> MaterialTheme.colorScheme.primary
        StatusChipTone.Warning -> MaterialTheme.colorScheme.error
        StatusChipTone.Neutral -> MaterialTheme.colorScheme.onSurfaceVariant
    }

    Row(
        modifier = modifier
            .clip(MindSenseThemeTokens.shapes.chip)
            .background(background)
            .padding(horizontal = 10.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .clip(MindSenseThemeTokens.shapes.chip)
                .background(content),
        )
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = content,
        )
    }
}
