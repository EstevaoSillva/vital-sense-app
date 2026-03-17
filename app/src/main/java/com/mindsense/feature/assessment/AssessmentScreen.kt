package com.mindsense.feature.assessment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.mindsense.core.designsystem.components.PrimaryButton
import com.mindsense.core.designsystem.components.SecondaryButton
import com.mindsense.core.designsystem.theme.MindSenseThemeTokens
import com.mindsense.core.ui.scaffold.MindSenseScaffold

@Composable
fun AssessmentScreen(
    navController: NavHostController,
    onBack: () -> Unit,
    onFinish: () -> Unit,
    viewModel: AssessmentViewModel = hiltViewModel(),
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value
    val question = state.questions.getOrNull(state.currentIndex) ?: return
    MindSenseScaffold(
        navController = navController,
        onBackClick = onBack,
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(MindSenseThemeTokens.spacing.lg),
            verticalArrangement = Arrangement.spacedBy(MindSenseThemeTokens.spacing.lg),
        ) {
            LinearProgressIndicator(
                progress = { (state.currentIndex + 1).toFloat() / state.questions.size.toFloat() },
                modifier = Modifier.fillMaxWidth(),
            )
            Text(question.category.uppercase(), style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
            Text(question.text, style = MaterialTheme.typography.headlineMedium)
            Column(verticalArrangement = Arrangement.spacedBy(MindSenseThemeTokens.spacing.sm)) {
                question.scaleLabels.forEachIndexed { index, label ->
                    SecondaryButton(
                        text = "${index + 1}. $label",
                        onClick = { viewModel.onAction(AssessmentAction.AnswerSelected(question.id, index + 1)) },
                    )
                }
            }
            PrimaryButton(
                text = if (state.currentIndex == state.questions.lastIndex) "Enviar respostas" else "Próxima",
                onClick = {
                    if (state.currentIndex == state.questions.lastIndex) onFinish()
                    else viewModel.onAction(AssessmentAction.Next)
                },
            )
        }
    }
}

@Composable
fun AssessmentResultPlaceholderScreen(
    navController: NavHostController,
    onOpenInsights: () -> Unit,
    onBackToDashboard: () -> Unit,
    viewModel: AssessmentResultViewModel = hiltViewModel(),
) {
    val result = viewModel.result.collectAsStateWithLifecycle().value ?: return
    MindSenseScaffold(
        title = "Resultado consolidado",
        navController = navController,
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(MindSenseThemeTokens.spacing.lg),
            verticalArrangement = Arrangement.spacedBy(MindSenseThemeTokens.spacing.md),
        ) {
            com.mindsense.core.designsystem.components.AppCard {
                Text("Classificação ${result.riskLabel}", style = MaterialTheme.typography.headlineMedium)
                Text(result.interpretation, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(top = MindSenseThemeTokens.spacing.sm))
                Text("Recomendação: ${result.mainRecommendation}", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(top = MindSenseThemeTokens.spacing.sm))
            }
            PrimaryButton(text = "Abrir insights", onClick = onOpenInsights)
            SecondaryButton(
                text = "Voltar ao dashboard",
                onClick = onBackToDashboard,
            )
        }
    }
}
