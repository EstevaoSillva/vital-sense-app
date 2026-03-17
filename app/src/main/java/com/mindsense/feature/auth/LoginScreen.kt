package com.mindsense.feature.auth

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.Modifier
import com.mindsense.core.ui.components.FeaturePlaceholderContent

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value
    FeaturePlaceholderContent(
        title = state.title,
        description = state.description,
        actionLabel = "Entrar",
        onActionClick = {
            viewModel.onAction(LoginAction.Submit(email = "demo@mindsense.app", password = "123456"))
            onLoginSuccess()
        },
        modifier = Modifier.fillMaxSize(),
    )
}
