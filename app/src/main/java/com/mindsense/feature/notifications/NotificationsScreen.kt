package com.mindsense.feature.notifications

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.mindsense.core.designsystem.components.AppCard
import com.mindsense.core.designsystem.components.FilterChipGroup
import com.mindsense.core.designsystem.components.SectionHeader
import com.mindsense.core.designsystem.components.StatusChip
import com.mindsense.core.designsystem.components.StatusChipTone
import com.mindsense.core.designsystem.theme.MindSenseThemeTokens
import com.mindsense.core.ui.feedback.EmptyState
import com.mindsense.core.ui.scaffold.MindSenseScaffold

@Composable
fun NotificationsScreen(
    navController: NavHostController,
    onBack: () -> Unit,
    viewModel: NotificationsViewModel = hiltViewModel(),
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value
    MindSenseScaffold(
        navController = navController,
        onBackClick = onBack,
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(MindSenseThemeTokens.spacing.lg),
            verticalArrangement = Arrangement.spacedBy(MindSenseThemeTokens.spacing.md),
        ) {
            item { SectionHeader(title = "Notificações") }
            item {
                FilterChipGroup(
                    options = listOf("Todos", "Stress", "Burnout", "Assessment", "Sync"),
                    selected = state.selectedFilter,
                    onSelected = { viewModel.onAction(NotificationsAction.FilterChanged(it)) },
                )
            }
            if (state.notifications.isEmpty()) {
                item {
                    EmptyState(
                        title = "Sem notificações",
                        description = "Nenhum alerta encontrado para esse filtro.",
                        actionLabel = "Ver todas",
                        onAction = { viewModel.onAction(NotificationsAction.FilterChanged("Todos")) },
                    )
                }
            }
            items(state.notifications) { notification ->
                AppCard {
                    Text(notification.title, style = MaterialTheme.typography.titleMedium)
                    Text(notification.description, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    StatusChip(
                        text = "${notification.category} • ${notification.timestamp}",
                        tone = if (notification.category == "Sync") StatusChipTone.Success else StatusChipTone.Info,
                        modifier = Modifier.padding(top = MindSenseThemeTokens.spacing.sm),
                    )
                }
            }
        }
    }
}
