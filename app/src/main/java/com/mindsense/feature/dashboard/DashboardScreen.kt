package com.mindsense.feature.dashboard

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import com.mindsense.core.designsystem.components.AppCard
import com.mindsense.core.designsystem.components.MetricCard
import com.mindsense.core.designsystem.components.PrimaryButton
import com.mindsense.core.designsystem.components.SecondaryButton
import com.mindsense.core.designsystem.components.SectionHeader
import com.mindsense.core.designsystem.components.SparklineChart
import com.mindsense.core.designsystem.components.StatusChip
import com.mindsense.core.designsystem.components.StatusChipTone
import com.mindsense.core.designsystem.components.StressGauge
import com.mindsense.core.designsystem.theme.MindSenseThemeTokens
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
    val snapshot = state.snapshot ?: return

    MindSenseScaffold(
        navController = navController,
        showBottomBar = true,
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(MindSenseThemeTokens.spacing.lg),
            verticalArrangement = Arrangement.spacedBy(MindSenseThemeTokens.spacing.md),
        ) {
            SectionHeader(title = "Bom dia, Lucas")
            AppCard {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text("Nível de Stress", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    StatusChip(text = "Ao vivo", tone = StatusChipTone.Success)
                }
                StressGauge(score = snapshot.score, modifier = Modifier.align(Alignment.CenterHorizontally))
                Text(
                    text = "Seu ${snapshot.deviceName} sincronizou ${snapshot.lastSyncLabel.lowercase()}.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                PrimaryButton(
                    text = "Abrir detalhe da coleta",
                    onClick = onOpenCollection,
                    modifier = Modifier.padding(top = MindSenseThemeTokens.spacing.md),
                )
            }
            Column(verticalArrangement = Arrangement.spacedBy(MindSenseThemeTokens.spacing.md)) {
                MetricCard(
                    label = "Risco Burnout",
                    value = "Baixo",
                    subtitle = "Estável",
                    tone = StatusChipTone.Success,
                )
                MetricCard(
                    label = "Última Sync",
                    value = "3 min",
                    subtitle = "Hoje",
                    tone = StatusChipTone.Info,
                )
            }
            AppCard {
                SectionHeader(title = "Tendência 7 dias")
                Spacer(modifier = Modifier.height(MindSenseThemeTokens.spacing.md))
                SparklineChart(
                    points = listOf(42, 38, 55, 48, 35, 40, 38),
                    lineColor = MindSenseThemeTokens.extendedColors.success,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(88.dp),
                )
            }
            SecondaryButton(text = "Ver insights", onClick = onOpenInsights)
            SecondaryButton(text = "Abrir notificações", onClick = onOpenNotifications)
            SecondaryButton(text = "Sincronização do relógio", onClick = onOpenSync)
            PrimaryButton(text = "Assessment de burnout", onClick = onOpenAssessment)
        }
    }
}
