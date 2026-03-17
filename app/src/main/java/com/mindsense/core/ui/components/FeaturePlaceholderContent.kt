package com.mindsense.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mindsense.core.designsystem.components.AppCard
import com.mindsense.core.designsystem.components.PrimaryButton
import com.mindsense.core.designsystem.theme.MindSenseThemeTokens

@Composable
fun FeaturePlaceholderContent(
    title: String,
    description: String,
    actionLabel: String,
    onActionClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(MindSenseThemeTokens.spacing.lg),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AppCard {
            Text(text = title, style = MaterialTheme.typography.headlineMedium)
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = MindSenseThemeTokens.spacing.sm),
            )
            PrimaryButton(
                text = actionLabel,
                onClick = onActionClick,
                modifier = Modifier.padding(top = MindSenseThemeTokens.spacing.lg),
            )
        }
    }
}
