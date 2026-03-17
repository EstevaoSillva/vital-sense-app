package com.mindsense.core.ui.components

import androidx.compose.runtime.Composable
import com.mindsense.core.designsystem.components.MindSenseTopAppBar

@Composable
fun MindSenseTopBar(
    title: String,
    onBackClick: (() -> Unit)? = null,
) {
    MindSenseTopAppBar(title = title, onBackClick = onBackClick)
}
