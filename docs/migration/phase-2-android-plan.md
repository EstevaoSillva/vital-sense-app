# Fase 2 - Planejamento da migração para Android

## Contexto
- Repositório Android atual: sem estrutura Android inicial ainda.
- Fonte funcional analisada: [phase-1-js-analysis.md](/home/estevaosilva/StudioProjects/vital-sense-app/docs/migration/phase-1-js-analysis.md)
- Objetivo desta fase: definir a arquitetura Android alvo antes de criar Gradle, app module e telas.

## Princípios da solução
- Arquitetura: MVVM + Clean-ish architecture
- UI: Jetpack Compose
- DI: Hilt
- Rede: Retrofit + OkHttp
- Assíncrono: Coroutines + StateFlow
- Navegação: Navigation Compose
- Persistência:
  - DataStore para preferências, sessão e flags leves
  - Room para cache local estruturado
- Estratégia de dados:
  - fake-first para espelhar o protótipo atual
  - interfaces de repositório já preparadas para backend Django real

## Árvore de diretórios sugerida

```text
app/
  src/main/java/com/mindsense/app/
    MindSenseApplication.kt
    MainActivity.kt
    di/
      AppModule.kt
      CoroutineModule.kt

core/
  ui/
    src/main/java/com/mindsense/core/ui/
      state/
        UiState.kt
      scaffold/
        MindSenseScaffold.kt
      components/
        ScreenContainer.kt
        MindSenseTopBar.kt
        MindSenseBottomBar.kt
      feedback/
        LoadingState.kt
        EmptyState.kt
        ErrorState.kt
      animation/
        FadeSlideTransition.kt

  designsystem/
    src/main/java/com/mindsense/core/designsystem/
      theme/
        Color.kt
        Typography.kt
        Shape.kt
        Spacing.kt
        Elevation.kt
        Theme.kt
      components/
        PrimaryButton.kt
        SecondaryButton.kt
        AppCard.kt
        SectionHeader.kt
        StatusChip.kt
        InputField.kt
        SearchBar.kt
        FilterChipGroup.kt
        MetricCard.kt
        GaugeCard.kt
        SparklineChart.kt

  common/
    src/main/java/com/mindsense/core/common/
      result/
        AppResult.kt
      dispatcher/
        DispatcherProvider.kt
      extensions/
      constants/
      util/

  network/
    src/main/java/com/mindsense/core/network/
      api/
        MindSenseApi.kt
      model/
        NetworkError.kt
      interceptor/
        AuthInterceptor.kt
        LoggingInterceptor.kt
      adapter/
      di/
        NetworkModule.kt

  datastore/
    src/main/java/com/mindsense/core/datastore/
      SessionPreferencesDataSource.kt
      UserPreferencesDataSource.kt
      OnboardingPreferencesDataSource.kt
      di/
        DataStoreModule.kt

  database/
    src/main/java/com/mindsense/core/database/
      MindSenseDatabase.kt
      dao/
        CollectionDao.kt
        ArticleDao.kt
        NotificationDao.kt
      entity/
        CollectionEntity.kt
        ArticleEntity.kt
        NotificationEntity.kt
      di/
        DatabaseModule.kt

  navigation/
    src/main/java/com/mindsense/core/navigation/
      AppDestination.kt
      AppNavHost.kt
      BottomDestination.kt
      NavGraph.kt

data/
  remote/
    src/main/java/com/mindsense/data/remote/
      datasource/
        AuthRemoteDataSource.kt
        DashboardRemoteDataSource.kt
        ContentRemoteDataSource.kt
        AssessmentRemoteDataSource.kt

  local/
    src/main/java/com/mindsense/data/local/
      datasource/
        SessionLocalDataSource.kt
        CollectionLocalDataSource.kt
        ArticleLocalDataSource.kt
        NotificationLocalDataSource.kt

  repository/
    src/main/java/com/mindsense/data/repository/
      AuthRepositoryImpl.kt
      DashboardRepositoryImpl.kt
      HistoryRepositoryImpl.kt
      InsightsRepositoryImpl.kt
      ProfileRepositoryImpl.kt
      ContentRepositoryImpl.kt
      AssessmentRepositoryImpl.kt
      SyncRepositoryImpl.kt
      NotificationRepositoryImpl.kt

  mapper/
    src/main/java/com/mindsense/data/mapper/
      CollectionMapper.kt
      ArticleMapper.kt
      NotificationMapper.kt
      AssessmentMapper.kt

  dto/
    src/main/java/com/mindsense/data/dto/
      auth/
      dashboard/
      history/
      insights/
      content/
      assessment/
      sync/

domain/
  model/
    src/main/java/com/mindsense/domain/model/
      UserProfile.kt
      StressSnapshot.kt
      CollectionSession.kt
      CollectionDetail.kt
      InsightSummary.kt
      Recommendation.kt
      ArticleSummary.kt
      ArticleDetail.kt
      NotificationItem.kt
      WatchSyncStatus.kt
      AssessmentQuestion.kt
      AssessmentAnswer.kt
      AssessmentResult.kt

  repository/
    src/main/java/com/mindsense/domain/repository/
      AuthRepository.kt
      DashboardRepository.kt
      HistoryRepository.kt
      InsightsRepository.kt
      ProfileRepository.kt
      ContentRepository.kt
      AssessmentRepository.kt
      SyncRepository.kt
      NotificationRepository.kt

  usecase/
    src/main/java/com/mindsense/domain/usecase/
      auth/
      dashboard/
      history/
      insights/
      profile/
      content/
      assessment/
      notifications/
      sync/

feature/
  onboarding/
    src/main/java/com/mindsense/feature/onboarding/
      OnboardingScreen.kt
      OnboardingViewModel.kt
      OnboardingUiState.kt
      OnboardingAction.kt
      components/

  auth/
    src/main/java/com/mindsense/feature/auth/
      LoginScreen.kt
      LoginViewModel.kt
      LoginUiState.kt
      LoginAction.kt
      components/

  dashboard/
    src/main/java/com/mindsense/feature/dashboard/
      DashboardScreen.kt
      DashboardViewModel.kt
      DashboardUiState.kt
      DashboardAction.kt
      components/

  history/
    src/main/java/com/mindsense/feature/history/
      HistoryScreen.kt
      HistoryViewModel.kt
      HistoryUiState.kt
      HistoryAction.kt
      CollectionDetailScreen.kt
      CollectionDetailViewModel.kt
      CollectionDetailUiState.kt
      CollectionDetailAction.kt
      components/

  insights/
    src/main/java/com/mindsense/feature/insights/
      InsightsScreen.kt
      InsightsViewModel.kt
      InsightsUiState.kt
      InsightsAction.kt
      RecommendationsScreen.kt
      RecommendationsViewModel.kt
      RecommendationsUiState.kt
      RecommendationsAction.kt
      components/

  profile/
    src/main/java/com/mindsense/feature/profile/
      ProfileScreen.kt
      ProfileViewModel.kt
      ProfileUiState.kt
      ProfileAction.kt
      components/

  notifications/
    src/main/java/com/mindsense/feature/notifications/
      NotificationsScreen.kt
      NotificationsViewModel.kt
      NotificationsUiState.kt
      NotificationsAction.kt
      components/

  sync/
    src/main/java/com/mindsense/feature/sync/
      SyncScreen.kt
      SyncViewModel.kt
      SyncUiState.kt
      SyncAction.kt
      components/

  content/
    src/main/java/com/mindsense/feature/content/
      ExploreScreen.kt
      ExploreViewModel.kt
      ExploreUiState.kt
      ExploreAction.kt
      ArticleScreen.kt
      ArticleViewModel.kt
      ArticleUiState.kt
      ArticleAction.kt
      components/

  assessment/
    src/main/java/com/mindsense/feature/assessment/
      AssessmentScreen.kt
      AssessmentViewModel.kt
      AssessmentUiState.kt
      AssessmentAction.kt
      AssessmentResultScreen.kt
      AssessmentResultViewModel.kt
      AssessmentResultUiState.kt
      AssessmentResultAction.kt
      components/

  shared/
    src/main/java/com/mindsense/feature/shared/
      fake/
        FakeSeedData.kt
```

## Organização por camada

### Presentation
- Vive em `feature/*`, `core/ui`, `core/designsystem`, `core/navigation`
- Responsabilidades:
  - renderização Compose
  - eventos de UI
  - coleta de `StateFlow`
  - navegação

### Domain
- Vive em `domain/*`
- Responsabilidades:
  - modelos estáveis do negócio
  - interfaces de repositório
  - casos de uso

### Data
- Vive em `data/*`, `core/network`, `core/database`, `core/datastore`
- Responsabilidades:
  - acesso a API
  - cache local
  - persistência
  - mapeamento DTO <-> entity <-> domain

## Estratégia de módulos

### Opção adotada para o bootstrap
- Início com módulo único Android (`app`) e pacotes internos seguindo a árvore acima.
- Motivo:
  - repositório está vazio
  - reduz custo de bootstrap e configuração Gradle
  - acelera entrega até telas e comportamentos

### Evolução prevista
- Se o app crescer, `core/*`, `data`, `domain` e `feature/*` podem virar módulos Gradle reais.
- A estrutura de pacotes será desenhada já pronta para essa extração futura.

## Estratégia de navegação

### Grafos
- `AuthGraph`
  - Onboarding
  - Login
- `MainGraph`
  - Dashboard
  - History
  - Insights
  - Explore
  - Profile
- `SecondaryGraph`
  - CollectionDetail
  - Recommendations
  - Notifications
  - Sync
  - Article
  - Assessment
  - AssessmentResult

### Regras
- Bottom bar só aparece no `MainGraph`.
- Telas de detalhe e fluxo linear usam top bar com voltar.
- Parâmetros de rota explícitos:
  - `collectionId`
  - `articleId`
- Logout limpa sessão e volta para `AuthGraph`.

### Destinations sugeridos
- `Onboarding`
- `Login`
- `Dashboard`
- `History`
- `CollectionDetail/{collectionId}`
- `Insights`
- `Recommendations`
- `Explore`
- `Article/{articleId}`
- `Sync`
- `Notifications`
- `Profile`
- `Assessment`
- `AssessmentResult`

## Estratégia de gerenciamento de estado

### Regra base
- Toda tela terá um `ViewModel` com `StateFlow<UiState>`.
- Eventos de usuário modelados como `Action` ou `Event`.
- Side effects isolados:
  - navegação
  - snackbar
  - envio ao relógio

### Estado de tela padrão
```kotlin
sealed interface UiState<out T> {
    data object Loading : UiState<Nothing>
    data class Content<T>(val data: T) : UiState<T>
    data class Empty(val message: String? = null) : UiState<Nothing>
    data class Error(val message: String, val canRetry: Boolean = true) : UiState<Nothing>
}
```

### Padrão por feature
- `FeatureUiState`
  - estado específico renderizável da feature
- `FeatureAction`
  - intenções do usuário
- `FeatureViewModel`
  - reduz ações em novo estado

### Diretrizes
- Nada de lógica de negócio em composables.
- Nada de composable consultando repositório.
- Transformação de dados de tela no `ViewModel` ou `usecase`.

## Estratégia para Room

### Objetivo
- cache offline de dados relevantes do protótipo e do backend futuro.

### Tabelas iniciais
- `collections`
- `articles`
- `notifications`

### Fase inicial
- Room pronto desde a infraestrutura
- DAOs básicos
- seeds fake opcionais para previews e demo

### Quando usar
- histórico de coletas
- artigos visitados e listas de conteúdo
- notificações recentes

### O que não vai para Room inicialmente
- estado efêmero do assessment em andamento
- preferências de sessão
- flags leves de onboarding

## Estratégia para DataStore

### Preferences a persistir
- `hasCompletedOnboarding`
- `isLoggedIn`
- `authToken` ou placeholder compatível
- `userName`
- preferências de notificações
- preferências de exibição no relógio

### Data sources sugeridos
- `OnboardingPreferencesDataSource`
- `SessionPreferencesDataSource`
- `UserPreferencesDataSource`

### Uso
- decidir destino inicial do app
- restaurar sessão simulada
- toggles do perfil e sync

## Estratégia para Retrofit + OkHttp

### Base
- `MindSenseApi` como contrato central
- `OkHttpClient` com:
  - logging interceptor
  - auth interceptor
  - timeouts razoáveis

### Serialização
- `kotlinx.serialization`
- motivo:
  - configuração mais direta com Kotlin
  - reduz boilerplate

### Política de erro
- mapear exceções e erros HTTP para `AppResult.Error`
- nunca expor resposta bruta diretamente à UI

### Contratos iniciais previstos
- `auth/login`
- `dashboard/summary`
- `history/collections`
- `history/collections/{id}`
- `insights`
- `recommendations`
- `content/articles`
- `content/articles/{id}`
- `notifications`
- `assessment/questions`
- `assessment/submit`
- `sync/status`

### Fase inicial
- Fake repositories serão a implementação padrão.
- `remote` entra com interfaces e stubs preparados para backend real.

## Estratégia de tema e design system

### Base visual derivada do JS
- dark theme como padrão inicial
- cards escuros com borda translúcida
- acentos:
  - azul elétrico
  - verde neon
  - magenta
- tipografia:
  - display forte e compacta
  - corpo neutro
  - números monoespaçados para métricas

### Tokens a criar
- `MindSenseColors`
- `MindSenseTypography`
- `MindSenseShapes`
- `MindSenseSpacing`
- `MindSenseElevation`

### Componentes base obrigatórios
- `PrimaryButton`
- `SecondaryButton`
- `AppCard`
- `SectionHeader`
- `MetricCard`
- `StatusChip`
- `SearchBar`
- `EmptyState`
- `ErrorState`
- `LoadingState`
- `MindSenseTopBar`
- `MindSenseBottomBar`
- `InputField`
- `FilterChipGroup`
- `StressGauge`
- `SparklineChart`

### Diretriz de adaptação
- preservar identidade visual do Lovable
- adaptar para padrões Android:
  - `Scaffold`
  - `TopAppBar`
  - `Snackbar`
  - navegação previsível
  - alvos de toque adequados

## Lista de módulos e arquivos iniciais

### Arquivos de projeto
- `settings.gradle.kts`
- `build.gradle.kts`
- `gradle.properties`
- `app/build.gradle.kts`
- `app/proguard-rules.pro`
- `app/src/main/AndroidManifest.xml`

### App bootstrap
- `MindSenseApplication.kt`
- `MainActivity.kt`

### DI
- `AppModule.kt`
- `CoroutineModule.kt`
- `NetworkModule.kt`
- `DatabaseModule.kt`
- `DataStoreModule.kt`
- `RepositoryModule.kt`

### Core
- `Theme.kt`
- `AppNavHost.kt`
- `AppDestination.kt`
- `UiState.kt`
- `MindSenseScaffold.kt`

### Data/domain base
- `AppResult.kt`
- `MindSenseApi.kt`
- `MindSenseDatabase.kt`
- `SessionPreferencesDataSource.kt`
- primeiros `Repository` interfaces e impls fake

### Features mínimas bootstrapadas
- Onboarding
- Auth
- Dashboard
- History
- Insights
- Explore
- Profile
- Notifications
- Sync
- Assessment

## Plano de implementação por etapas

### Etapa 1 - Bootstrap do projeto
- criar Gradle raiz e módulo `app`
- configurar Compose, Kotlin, Hilt, Retrofit, Room, DataStore
- configurar `Application`, `MainActivity` e `AndroidManifest`

### Etapa 2 - Core técnico
- criar tema, tokens e `Scaffold`
- criar root navigation
- criar `UiState` padrão
- criar módulos de DI

### Etapa 3 - Data foundation
- contratos de repositório em `domain`
- fake repositories em `data`
- `AppResult`
- Room base
- DataStore base
- Retrofit base

### Etapa 4 - Design system Compose
- traduzir paleta, tipografia, spacing, shapes e elevação
- criar componentes base reutilizáveis
- criar gauge e sparkline reutilizáveis

### Etapa 5 - Features prioritárias
- onboarding
- auth
- dashboard
- history + detail
- insights + recommendations
- explore + article
- notifications
- sync
- assessment + result
- profile

### Etapa 6 - Comportamentos
- filtros, busca, tabs, seleção, retry, feedbacks
- ativar `loading`, `empty` e `error` mesmo onde o protótipo não exercita

### Etapa 7 - Preparação para backend real
- DTOs
- mappers
- remote data sources
- cache policy
- TODOs explícitos para integração Django

## Mapeamento das features do JS para features Android

| JS | Feature Android |
|---|---|
| Onboarding | `feature/onboarding` |
| Login | `feature/auth` |
| Dashboard | `feature/dashboard` |
| History + CollectionDetail | `feature/history` |
| Insights + Recommendations | `feature/insights` |
| Explore + Article | `feature/content` |
| SyncWatch | `feature/sync` |
| Notifications | `feature/notifications` |
| Profile | `feature/profile` |
| Assessment + AssessmentResult | `feature/assessment` |

## Decisões de adaptação

### Decisão 1
- `Recommendations` ficará junto de `insights`, não como feature isolada.
- Motivo: no protótipo é desdobramento direto da leitura analítica.

### Decisão 2
- `CollectionDetail` ficará dentro de `history`.
- Motivo: modelagem e navegação pertencem ao contexto de sessões/coletas.

### Decisão 3
- `Article` ficará dentro de `content`.
- Motivo: é detalhe direto de descoberta de conteúdo.

### Decisão 4
- `AssessmentResult` fica na mesma feature do `Assessment`.
- Motivo: fluxo linear e compartilhamento de modelos.

## Riscos e mitigação

### Risco
- O protótipo não define contratos de backend.
- Mitigação:
  - domínio forte
  - fake repositories
  - DTOs preparados e desacoplados

### Risco
- Algumas telas têm intenção de UX maior que comportamento implementado.
- Mitigação:
  - documentar adaptações por tela
  - criar estados explícitos de tela no Android

### Risco
- Gauge e sparkline exigem custom UI em Compose.
- Mitigação:
  - encapsular em componentes de design system
  - manter API simples para reuso

## Resumo
- A base será montada em módulo único com pacotes preparados para futura modularização.
- A navegação será separada em auth, main e rotas secundárias.
- Toda tela terá `ViewModel + UiState + Action`.
- Room e DataStore entram já na Fase 3.
- Retrofit entra pronto, mas com uso inicial via fake repositories.
- O design system Compose será extraído diretamente dos tokens observados no protótipo JS.

## Próximo passo
- Fase 3: bootstrap da infraestrutura Android, criando projeto compilável, dependências, DI, navegação raiz, tema e estados base sem implementar telas finais ainda.
