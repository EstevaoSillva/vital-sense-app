package com.mindsense.feature.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.mindsense.core.designsystem.components.AppCard
import com.mindsense.core.designsystem.components.FilterChipGroup
import com.mindsense.core.designsystem.components.SearchBar
import com.mindsense.core.designsystem.components.SectionHeader
import com.mindsense.core.designsystem.components.StatusChip
import com.mindsense.core.designsystem.components.StatusChipTone
import com.mindsense.core.designsystem.theme.MindSenseThemeTokens
import com.mindsense.core.ui.scaffold.MindSenseScaffold

@Composable
fun ExploreScreen(
    navController: NavHostController,
    onOpenArticle: () -> Unit,
    viewModel: ContentViewModel = hiltViewModel(),
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value
    var query by remember { mutableStateOf("") }
    MindSenseScaffold(
        navController = navController,
        showBottomBar = true,
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(MindSenseThemeTokens.spacing.lg),
            verticalArrangement = Arrangement.spacedBy(MindSenseThemeTokens.spacing.md),
        ) {
            item { SectionHeader(title = "Explorar") }
            item {
                SearchBar(
                    value = query,
                    onValueChange = { query = it },
                    placeholder = "Pesquisar sobre burnout, stress...",
                )
            }
            item {
                FilterChipGroup(
                    options = listOf("O que é burnout", "Sintomas", "Prevenção", "Sono"),
                    selected = "O que é burnout",
                    onSelected = {},
                )
            }
            items(state.articles) { article ->
                AppCard(modifier = Modifier.fillMaxWidth()) {
                    Text(article.category.uppercase(), style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                    Text(article.title, style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(top = MindSenseThemeTokens.spacing.sm))
                    StatusChip(
                        text = "${article.readTimeMinutes} min de leitura",
                        tone = StatusChipTone.Info,
                        modifier = Modifier.padding(top = MindSenseThemeTokens.spacing.sm),
                    )
                    androidx.compose.material3.TextButton(onClick = onOpenArticle) {
                        Text("Abrir artigo")
                    }
                }
            }
        }
    }
}

@Composable
fun ArticlePlaceholderScreen(
    navController: NavHostController,
    onBack: () -> Unit,
    viewModel: ArticleViewModel = hiltViewModel(),
) {
    val article = viewModel.article.collectAsStateWithLifecycle().value ?: return
    MindSenseScaffold(
        title = "Artigo",
        navController = navController,
        onBackClick = onBack,
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(MindSenseThemeTokens.spacing.lg),
            verticalArrangement = Arrangement.spacedBy(MindSenseThemeTokens.spacing.md),
        ) {
            item {
                Text(article.category.uppercase(), style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                Text(article.title, style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(top = MindSenseThemeTokens.spacing.sm))
                Text(article.summary, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(top = MindSenseThemeTokens.spacing.sm))
            }
            item {
                AppCard {
                    Text("Resumo", style = MaterialTheme.typography.titleMedium)
                    Text(article.summary, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(top = MindSenseThemeTokens.spacing.sm))
                }
            }
            items(article.sections) { section ->
                AppCard {
                    Text(section.heading, style = MaterialTheme.typography.titleMedium)
                    Text(section.body, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(top = MindSenseThemeTokens.spacing.sm))
                }
            }
            item {
                AppCard {
                    Text("Resumo para relógio", style = MaterialTheme.typography.titleMedium)
                    Text(article.watchSummary, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(top = MindSenseThemeTokens.spacing.sm))
                }
            }
        }
    }
}
