package com.mindsense.feature.history

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
fun HistoryScreen(
    navController: NavHostController,
    onOpenCollection: () -> Unit,
    viewModel: HistoryViewModel = hiltViewModel(),
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
                actionLabel = "Abrir detalhe",
                onActionClick = onOpenCollection,
            )
        }
    }
}

@Composable
fun CollectionDetailPlaceholderScreen(
    navController: NavHostController,
    onBack: () -> Unit,
) {
    MindSenseScaffold(
        title = "Detalhe da coleta",
        navController = navController,
        onBackClick = onBack,
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            FeaturePlaceholderContent(
                title = "Detalhe de coleta",
                description = "Rota parametrizada pronta (`collectionId`) e desacoplada do shell principal.",
                actionLabel = "Voltar",
                onActionClick = onBack,
            )
        }
    }
}
