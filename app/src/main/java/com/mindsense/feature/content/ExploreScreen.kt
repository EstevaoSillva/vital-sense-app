package com.mindsense.feature.content

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
fun ExploreScreen(
    navController: NavHostController,
    onOpenArticle: () -> Unit,
    viewModel: ContentViewModel = hiltViewModel(),
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
                actionLabel = "Abrir artigo",
                onActionClick = onOpenArticle,
            )
        }
    }
}

@Composable
fun ArticlePlaceholderScreen(
    navController: NavHostController,
    onBack: () -> Unit,
) {
    MindSenseScaffold(
        title = "Artigo",
        navController = navController,
        onBackClick = onBack,
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            FeaturePlaceholderContent(
                title = "Leitura de conteúdo",
                description = "Rota `articleId` pronta para leitura longa, CTA para relógio e relacionados.",
                actionLabel = "Voltar",
                onActionClick = onBack,
            )
        }
    }
}
