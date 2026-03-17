package com.mindsense.feature.insights

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.mindsense.core.designsystem.components.SectionHeader
import com.mindsense.core.designsystem.components.StatusChip
import com.mindsense.core.designsystem.components.StatusChipTone
import com.mindsense.core.designsystem.components.SparklineChart
import com.mindsense.core.designsystem.theme.MindSenseThemeTokens
import com.mindsense.core.ui.scaffold.MindSenseScaffold

@Composable
fun InsightsScreen(
    navController: NavHostController,
    onOpenRecommendations: () -> Unit,
    viewModel: InsightsViewModel = hiltViewModel(),
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value
    val summary = state.summary ?: return
    MindSenseScaffold(
        navController = navController,
        showBottomBar = true,
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(MindSenseThemeTokens.spacing.lg),
            verticalArrangement = Arrangement.spacedBy(MindSenseThemeTokens.spacing.md),
        ) {
            item { SectionHeader(title = "Insights") }
            item {
                AppCard {
                    SectionHeader(title = "Stress semanal")
                    SparklineChart(
                        points = summary.weeklyStress,
                        lineColor = MindSenseThemeTokens.extendedColors.success,
                        modifier = Modifier.fillMaxWidth().height(88.dp),
                    )
                }
            }
            item {
                AppCard {
                    SectionHeader(title = "Evolução mensal")
                    SparklineChart(
                        points = summary.monthlyStress,
                        lineColor = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.fillMaxWidth().height(88.dp),
                    )
                }
            }
            item {
                AppCard {
                    SectionHeader(title = "Fatores críticos")
                    Column(verticalArrangement = Arrangement.spacedBy(MindSenseThemeTokens.spacing.sm)) {
                        summary.criticalFactors.forEach {
                            StatusChip(text = it, tone = StatusChipTone.Warning)
                        }
                    }
                    androidx.compose.material3.TextButton(onClick = onOpenRecommendations) {
                        Text("Ver recomendações")
                    }
                }
            }
        }
    }
}

@Composable
fun RecommendationsPlaceholderScreen(
    navController: NavHostController,
    onBack: () -> Unit,
    viewModel: InsightsViewModel = hiltViewModel(),
) {
    val recommendations = viewModel.uiState.collectAsStateWithLifecycle().value.recommendations
    MindSenseScaffold(
        title = "Recomendações",
        navController = navController,
        onBackClick = onBack,
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(MindSenseThemeTokens.spacing.lg),
            verticalArrangement = Arrangement.spacedBy(MindSenseThemeTokens.spacing.md),
        ) {
            items(recommendations) { recommendation ->
                AppCard {
                    Text(recommendation.title, style = MaterialTheme.typography.titleMedium)
                    Text(recommendation.description, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text("Motivo: ${recommendation.reason}", style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(top = MindSenseThemeTokens.spacing.sm))
                }
            }
        }
    }
}
