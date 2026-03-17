package com.mindsense.feature.notifications

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.mindsense.core.ui.components.FeaturePlaceholderContent
import com.mindsense.core.ui.scaffold.MindSenseScaffold

@Composable
fun NotificationsScreen(
    navController: NavHostController,
    onBack: () -> Unit,
    viewModel: NotificationsViewModel = hiltViewModel(),
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value
    MindSenseScaffold(
        title = state.title,
        navController = navController,
        onBackClick = onBack,
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            FeaturePlaceholderContent(
                title = state.title,
                description = state.description,
                actionLabel = "Voltar",
                onActionClick = onBack,
            )
        }
    }
}
