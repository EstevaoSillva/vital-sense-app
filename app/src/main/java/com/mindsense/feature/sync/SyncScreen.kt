package com.mindsense.feature.sync

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.mindsense.core.designsystem.components.PrimaryButton
import com.mindsense.core.designsystem.components.SectionHeader
import com.mindsense.core.designsystem.components.SecondaryButton
import com.mindsense.core.designsystem.components.StatusChip
import com.mindsense.core.designsystem.components.StatusChipTone
import com.mindsense.core.designsystem.theme.MindSenseThemeTokens
import com.mindsense.core.ui.scaffold.MindSenseScaffold

@Composable
fun SyncScreen(
    navController: NavHostController,
    onBack: () -> Unit,
    viewModel: SyncViewModel = hiltViewModel(),
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value
    val status = state.status ?: return
    MindSenseScaffold(
        title = "Sincronização",
        navController = navController,
        onBackClick = onBack,
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(MindSenseThemeTokens.spacing.lg),
            verticalArrangement = Arrangement.spacedBy(MindSenseThemeTokens.spacing.md),
        ) {
            item {
                AppCard {
                    SectionHeader(title = status.deviceName)
                    StatusChip(
                        text = if (status.isConnected) "Conectado" else "Desconectado",
                        tone = if (status.isConnected) StatusChipTone.Success else StatusChipTone.Warning,
                    )
                    Text("Bateria ${status.batteryPercent}%", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(top = MindSenseThemeTokens.spacing.sm))
                    Text(status.lastSyncLabel, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    state.infoMessage?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(top = MindSenseThemeTokens.spacing.sm),
                        )
                    }
                }
            }
            item {
                AppCard {
                    Text("Permissões e sensores", style = MaterialTheme.typography.titleMedium)
                    Column(verticalArrangement = Arrangement.spacedBy(MindSenseThemeTokens.spacing.xs), modifier = Modifier.padding(top = MindSenseThemeTokens.spacing.sm)) {
                        listOf("Frequência cardíaca", "Acelerômetro", "Temperatura", "SpO2").forEach {
                            Text("• $it", style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
            item { PrimaryButton(text = "Sincronizar agora", onClick = { viewModel.onAction(SyncAction.SyncNow) }) }
            item { SecondaryButton(text = "Testar conexão", onClick = { viewModel.onAction(SyncAction.TestConnection) }) }
            item { SecondaryButton(text = "Configurar dados do relógio", onClick = { viewModel.onAction(SyncAction.OpenSettings) }) }
        }
    }
}
