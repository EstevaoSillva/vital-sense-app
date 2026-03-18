package com.mindsense.core.ui.feedback

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mindsense.core.designsystem.components.EmptyState as DsEmptyState

@Composable
fun EmptyState(
    title: String,
    description: String,
    actionLabel: String? = null,
    onAction: (() -> Unit)? = null,
) {
    DsEmptyState(
        title = title,
        description = description,
        modifier = Modifier,
        actionLabel = actionLabel,
        onActionClick = onAction,
    )
}
