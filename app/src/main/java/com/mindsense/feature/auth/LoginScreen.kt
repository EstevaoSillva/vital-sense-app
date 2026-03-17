package com.mindsense.feature.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Memory
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mindsense.core.designsystem.components.InputField
import com.mindsense.core.designsystem.components.PrimaryButton
import com.mindsense.core.designsystem.components.SecondaryButton
import com.mindsense.core.designsystem.theme.MindSenseThemeTokens

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = MindSenseThemeTokens.spacing.lg, vertical = MindSenseThemeTokens.spacing.xxl),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MindSenseThemeTokens.spacing.xxl),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            androidx.compose.foundation.layout.Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.12f), CircleShape)
                    .padding(18.dp),
            ) {
                Icon(Icons.Rounded.Memory, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            }
            Text(
                text = "MindSense AI",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(top = MindSenseThemeTokens.spacing.lg),
            )
            Text(
                text = "Sua biometria, traduzida.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MindSenseThemeTokens.spacing.xxl),
            verticalArrangement = Arrangement.spacedBy(MindSenseThemeTokens.spacing.md),
        ) {
            InputField(
                value = state.email,
                onValueChange = { viewModel.onAction(LoginAction.EmailChanged(it)) },
                label = "E-mail",
                placeholder = "seu@email.com",
            )
            InputField(
                value = state.password,
                onValueChange = { viewModel.onAction(LoginAction.PasswordChanged(it)) },
                label = "Senha",
                placeholder = "••••••••",
                visualTransformation = if (state.showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { viewModel.onAction(LoginAction.TogglePasswordVisibility) }) {
                        Icon(
                            imageVector = if (state.showPassword) Icons.Rounded.VisibilityOff else Icons.Rounded.Visibility,
                            contentDescription = "Alternar visibilidade",
                        )
                    }
                },
            )
            Text(
                text = "Esqueci minha senha",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.align(Alignment.End),
            )
            PrimaryButton(
                text = "Entrar",
                onClick = {
                    viewModel.onAction(LoginAction.Submit(state.email, state.password))
                    onLoginSuccess()
                },
            )
            SecondaryButton(
                text = "Entrar com biometria",
                onClick = {
                    viewModel.onAction(LoginAction.Submit(state.email, state.password))
                    onLoginSuccess()
                },
            )
        }
        Text(
            text = "Seus dados são protegidos com criptografia de ponta a ponta.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}
