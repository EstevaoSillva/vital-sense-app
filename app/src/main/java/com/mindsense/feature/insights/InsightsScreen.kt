package com.mindsense.feature.insights

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
fun InsightsScreen(
    navController: NavHostController,
    onOpenRecommendations: () -> Unit,
    viewModel: InsightsViewModel = hiltViewModel(),
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value
    MindSenseScaffold(
        title = state.title,
        navController = navController,
        showBottomBar = true,
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            FeaturePlaceholderContent(
                title = state.title,
                description = state.description,
                actionLabel = "Ver recomendações",
                onActionClick = onOpenRecommendations,
            )
        }
    }
}

@Composable
fun RecommendationsPlaceholderScreen(
    navController: NavHostController,
    onBack: () -> Unit,
) {
    MindSenseScaffold(
        title = "Recomendações",
        navController = navController,
        onBackClick = onBack,
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            FeaturePlaceholderContent(
                title = "Recomendações",
                description = "Feature agrupada com Insights e pronta para filtros reais na Fase 6.",
                actionLabel = "Voltar",
                onActionClick = onBack,
            )
        }
    }
}
