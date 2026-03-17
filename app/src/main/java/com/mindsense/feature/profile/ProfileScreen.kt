package com.mindsense.feature.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.mindsense.core.designsystem.components.AppCard
import com.mindsense.core.designsystem.components.SectionHeader
import com.mindsense.core.designsystem.components.SecondaryButton
import com.mindsense.core.designsystem.theme.MindSenseThemeTokens
import com.mindsense.core.ui.scaffold.MindSenseScaffold

@Composable
fun ProfileScreen(
    navController: NavHostController,
    onLogout: () -> Unit,
    onOpenSync: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value
    val profile = state.profile ?: return
    MindSenseScaffold(
        navController = navController,
        showBottomBar = true,
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(MindSenseThemeTokens.spacing.lg),
            verticalArrangement = Arrangement.spacedBy(MindSenseThemeTokens.spacing.md),
        ) {
            item { SectionHeader(title = "Perfil") }
            item {
                AppCard(modifier = Modifier.fillMaxWidth()) {
                    Text(profile.name, style = MaterialTheme.typography.headlineMedium)
                    Text(profile.email, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
            item {
                AppCard {
                    Text("Cargo", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(profile.jobTitle, style = MaterialTheme.typography.bodyLarge)
                    Text("Empresa", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(top = MindSenseThemeTokens.spacing.md))
                    Text(profile.company, style = MaterialTheme.typography.bodyLarge)
                    Text("Jornada", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(top = MindSenseThemeTokens.spacing.md))
                    Text(profile.workSchedule, style = MaterialTheme.typography.bodyLarge)
                }
            }
            item { SecondaryButton(text = "Dispositivos conectados", onClick = onOpenSync) }
            item {
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
}
