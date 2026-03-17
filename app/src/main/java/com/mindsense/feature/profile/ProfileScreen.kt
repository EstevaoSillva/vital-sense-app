package com.mindsense.feature.profile

import androidx.compose.foundation.layout.Arrangement
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
fun ProfileScreen(
    navController: NavHostController,
    onLogout: () -> Unit,
    onOpenSync: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value
    MindSenseScaffold(
        title = state.title,
        navController = navController,
        showBottomBar = true,
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(MindSenseThemeTokens.spacing.lg),
            verticalArrangement = Arrangement.spacedBy(MindSenseThemeTokens.spacing.md),
        ) {
            FeaturePlaceholderContent(
                title = state.title,
                description = state.description,
                actionLabel = "Abrir dispositivos conectados",
                onActionClick = onOpenSync,
            )
            SecondaryButton(
                text = "Sair da conta",
                onClick = {
                    viewModel.onAction(ProfileAction.Logout)
                    onLogout()
                },
            )
        }
    }
}
