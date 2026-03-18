package com.mindsense.feature.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.BluetoothSearching
import androidx.compose.material.icons.rounded.BluetoothSearching
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Insights
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mindsense.core.designsystem.components.PrimaryButton
import com.mindsense.core.designsystem.theme.MindSenseThemeTokens

private data class OnboardingSlide(
    val icon: ImageVector,
    val title: String,
    val description: String,
    val color: Color,
)

@Composable
fun OnboardingScreen(
    onContinue: () -> Unit,
    viewModel: OnboardingViewModel = hiltViewModel(),
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value
    val slides = listOf(
        OnboardingSlide(
            icon = Icons.Rounded.Favorite,
            title = "Monitore seus sinais vitais",
            description = "Acompanhe frequência cardíaca, HRV e outros indicadores em tempo real.",
            color = MindSenseThemeTokens.extendedColors.success,
        ),
        OnboardingSlide(
            icon = Icons.Rounded.Insights,
            title = "Análises e tendências personalizadas",
            description = "Receba insights sobre stress, risco de burnout e comportamento fisiológico.",
            color = MaterialTheme.colorScheme.primary,
        ),
        OnboardingSlide(
            icon = Icons.AutoMirrored.Rounded.BluetoothSearching,
            title = "Celular e relógio conectados",
            description = "Sincronize dados com seu smartwatch e leve resumos para o pulso.",
            color = MaterialTheme.colorScheme.error,
        ),
    )
    val slide = slides[state.currentIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(MindSenseThemeTokens.spacing.lg),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            TextButton(
                onClick = {
                    viewModel.onAction(OnboardingAction.SkipClicked)
                    onContinue()
                },
            ) {
                Text(
                    text = "Pular",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Box(
                modifier = Modifier
                    .size(112.dp)
                    .background(slide.color.copy(alpha = 0.12f), CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = slide.icon,
                    contentDescription = null,
                    tint = slide.color,
                    modifier = Modifier.size(42.dp),
                )
            }
            Text(
                text = slide.title,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(top = MindSenseThemeTokens.spacing.xl),
            )
            Text(
                text = slide.description,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = MindSenseThemeTokens.spacing.md),
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MindSenseThemeTokens.spacing.xl),
            horizontalArrangement = Arrangement.Center,
        ) {
            slides.forEachIndexed { index, _ ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = MindSenseThemeTokens.spacing.xs)
                        .background(
                            color = if (index == state.currentIndex) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                            shape = CircleShape,
                        )
                        .size(
                            width = if (index == state.currentIndex) 24.dp else 8.dp,
                            height = 8.dp,
                        )
                        .height(8.dp),
                )
            }
        }
        PrimaryButton(
            text = if (state.currentIndex == slides.lastIndex) "Começar" else "Continuar",
            onClick = {
                val last = state.currentIndex == slides.lastIndex
                viewModel.onAction(OnboardingAction.ContinueClicked)
                if (last) onContinue()
            },
        )
    }
}
