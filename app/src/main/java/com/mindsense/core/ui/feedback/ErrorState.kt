package com.mindsense.core.ui.feedback

import androidx.compose.runtime.Composable
import com.mindsense.core.designsystem.components.ErrorState as DsErrorState

@Composable
fun ErrorState(
    title: String,
    description: String,
    onRetry: (() -> Unit)? = null,
) {
    DsErrorState(title = title, description = description, onRetry = onRetry)
}
