package com.mindsense.feature.assessment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.mindsense.core.designsystem.components.SecondaryButton
import com.mindsense.core.designsystem.theme.MindSenseThemeTokens
import com.mindsense.core.ui.components.FeaturePlaceholderContent
import com.mindsense.core.ui.scaffold.MindSenseScaffold

@Composable
fun AssessmentScreen(
    navController: NavHostController,
    onBack: () -> Unit,
    onFinish: () -> Unit,
    viewModel: AssessmentViewModel = hiltViewModel(),
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
                actionLabel = "Enviar assessment",
                onActionClick = {
                    viewModel.onAction(AssessmentAction.Submit)
                    onFinish()
                },
            )
        }
    }
}

@Composable
fun AssessmentResultPlaceholderScreen(
    navController: NavHostController,
    onOpenInsights: () -> Unit,
    onBackToDashboard: () -> Unit,
) {
    MindSenseScaffold(
        title = "Resultado consolidado",
        navController = navController,
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(MindSenseThemeTokens.spacing.lg),
            verticalArrangement = Arrangement.spacedBy(MindSenseThemeTokens.spacing.md),
        ) {
            FeaturePlaceholderContent(
                title = "Resultado do assessment",
                description = "Rota pronta para score consolidado, fatores de atenção e CTAs finais.",
                actionLabel = "Abrir insights",
                onActionClick = onOpenInsights,
            )
            SecondaryButton(
                text = "Voltar ao dashboard",
                onClick = onBackToDashboard,
            )
        }
    }
}
