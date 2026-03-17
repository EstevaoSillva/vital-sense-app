package com.mindsense.core.ui.scaffold

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.material3.Scaffold
import com.mindsense.core.ui.components.MindSenseBottomBar
import com.mindsense.core.ui.components.MindSenseTopBar

@Composable
fun MindSenseScaffold(
    title: String? = null,
    navController: NavHostController? = null,
    showBottomBar: Boolean = false,
    onBackClick: (() -> Unit)? = null,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        topBar = {
            if (title != null) {
                MindSenseTopBar(title = title, onBackClick = onBackClick)
            }
        },
        bottomBar = {
            if (showBottomBar && navController != null) {
                MindSenseBottomBar(navController = navController)
            }
        },
        content = content,
    )
}
