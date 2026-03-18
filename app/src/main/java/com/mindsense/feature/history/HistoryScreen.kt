package com.mindsense.feature.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
fun HistoryScreen(
    navController: NavHostController,
    onOpenCollection: (String) -> Unit,
    viewModel: HistoryViewModel = hiltViewModel(),
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value
    MindSenseScaffold(
        navController = navController,
        showBottomBar = true,
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(MindSenseThemeTokens.spacing.lg),
            verticalArrangement = Arrangement.spacedBy(MindSenseThemeTokens.spacing.md),
        ) {
            item {
                SectionHeader(title = "Histórico")
            }
            item {
                FilterChipGroup(
                    options = listOf("Hoje", "7 dias", "30 dias", "Todos"),
                    selected = state.selectedFilter,
                    onSelected = { viewModel.onAction(HistoryAction.FilterChanged(it)) },
                )
            }
            if (state.collections.isEmpty()) {
                item {
                    EmptyState(
                        title = "Nenhuma coleta encontrada",
                        description = "Ajuste o período para visualizar outras sessões.",
                        actionLabel = "Ver todas",
                        onAction = { viewModel.onAction(HistoryAction.FilterChanged("Todos")) },
                    )
                }
            }
            items(state.collections) { item ->
                AppCard(modifier = Modifier.fillMaxWidth()) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Column(verticalArrangement = Arrangement.spacedBy(MindSenseThemeTokens.spacing.xs)) {
                            Text(item.timestamp, style = MaterialTheme.typography.titleMedium)
                            Text(
                                "${item.durationLabel} • ${item.deviceName}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                        StatusChip(
                            text = item.label,
                            tone = if (item.score <= 40) StatusChipTone.Success else StatusChipTone.Warning,
                        )
                    }
                    Text(
                        text = "Score ${item.score} • Qualidade ${item.quality}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(top = MindSenseThemeTokens.spacing.sm),
                    )
                    androidx.compose.material3.TextButton(
                        onClick = { onOpenCollection(item.id) },
                        modifier = Modifier.padding(top = MindSenseThemeTokens.spacing.sm),
                    ) { Text("Abrir detalhe") }
                }
            }
        }
    }
}

@Composable
fun CollectionDetailPlaceholderScreen(
    navController: NavHostController,
    onBack: () -> Unit,
    viewModel: CollectionDetailViewModel = hiltViewModel(),
) {
    val detail = viewModel.detail.collectAsStateWithLifecycle().value ?: return
    MindSenseScaffold(
        title = "Detalhe da coleta",
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
                    Text(detail.session.title, style = MaterialTheme.typography.headlineMedium)
                    Text(detail.session.timestamp, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    com.mindsense.core.designsystem.components.StressGauge(
                        score = detail.session.score,
                        modifier = Modifier.align(androidx.compose.ui.Alignment.CenterHorizontally),
                    )
                }
            }
            item {
                AppCard {
                    SectionHeader(title = "Frequência cardíaca")
                    com.mindsense.core.designsystem.components.SparklineChart(
                        points = detail.heartRatePoints,
                        lineColor = MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(88.dp),
                    )
                }
            }
            item {
                AppCard {
                    SectionHeader(title = "Stress na sessão")
                    com.mindsense.core.designsystem.components.SparklineChart(
                        points = detail.stressPoints,
                        lineColor = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(88.dp),
                    )
                }
            }
            item {
                AppCard {
                    Text("Sensores: ${detail.sensors.joinToString()}", style = MaterialTheme.typography.bodyMedium)
                    Text(detail.observation, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(top = MindSenseThemeTokens.spacing.sm))
                    Text(detail.recommendation, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(top = MindSenseThemeTokens.spacing.sm))
                }
            }
        }
    }
}
