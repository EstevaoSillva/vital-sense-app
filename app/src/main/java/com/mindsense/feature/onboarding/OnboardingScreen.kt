package com.mindsense.feature.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.Modifier
import com.mindsense.core.ui.components.FeaturePlaceholderContent

@Composable
fun OnboardingScreen(
    onContinue: () -> Unit,
    viewModel: OnboardingViewModel = hiltViewModel(),
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value
    FeaturePlaceholderContent(
        title = state.title,
        description = state.description,
        actionLabel = "Continuar",
        onActionClick = {
            viewModel.onAction(OnboardingAction.ContinueClicked)
            onContinue()
        },
        modifier = Modifier.fillMaxSize(),
    )
}
