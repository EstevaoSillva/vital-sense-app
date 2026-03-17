package com.mindsense.feature.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.mindsense.core.designsystem.components.PrimaryButton
import com.mindsense.core.designsystem.components.SecondaryButton
import com.mindsense.core.designsystem.theme.MindSenseThemeTokens
import com.mindsense.core.ui.components.FeaturePlaceholderContent
import com.mindsense.core.ui.scaffold.MindSenseScaffold

@Composable
fun DashboardScreen(
    navController: NavHostController,
    onOpenCollection: () -> Unit,
    onOpenInsights: () -> Unit,
    onOpenNotifications: () -> Unit,
    onOpenSync: () -> Unit,
    onOpenAssessment: () -> Unit,
    viewModel: DashboardViewModel = hiltViewModel(),
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value

    MindSenseScaffold(
        title = state.title,
        navController = navController,
        showBottomBar = true,
    ) { padding ->
        DashboardContent(
            padding = padding,
            description = state.description,
            onOpenCollection = onOpenCollection,
            onOpenInsights = onOpenInsights,
            onOpenNotifications = onOpenNotifications,
            onOpenSync = onOpenSync,
            onOpenAssessment = onOpenAssessment,
        )
    }
}

@Composable
private fun DashboardContent(
    padding: PaddingValues,
    description: String,
    onOpenCollection: () -> Unit,
    onOpenInsights: () -> Unit,
    onOpenNotifications: () -> Unit,
    onOpenSync: () -> Unit,
    onOpenAssessment: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(MindSenseThemeTokens.spacing.lg),
        verticalArrangement = Arrangement.spacedBy(MindSenseThemeTokens.spacing.md),
    ) {
        FeaturePlaceholderContent(
            title = "Infraestrutura da home pronta",
            description = description,
            actionLabel = "Abrir detalhe da coleta",
            onActionClick = onOpenCollection,
        )
        PrimaryButton(text = "Ver insights", onClick = onOpenInsights)
        SecondaryButton(text = "Abrir notificações", onClick = onOpenNotifications)
        SecondaryButton(text = "Ir para sincronização", onClick = onOpenSync)
        SecondaryButton(text = "Iniciar assessment", onClick = onOpenAssessment)
    }
}
