# Fase 3 - Bootstrap da infraestrutura Android

## O que foi criado

### Projeto Android
- `settings.gradle.kts`
  - declara repositórios e módulo `:app`
- `build.gradle.kts`
  - centraliza versões de plugins Android, Kotlin, Compose, Hilt e KSP
- `gradle.properties`
  - propriedades base do projeto
- `app/build.gradle.kts`
  - configura Android app, Compose, Hilt, Retrofit, Room, DataStore e Navigation
- `app/proguard-rules.pro`
  - placeholder para release

### Bootstrap do app
- `app/src/main/AndroidManifest.xml`
  - registra `Application`, `MainActivity` e permissões
- `app/src/main/java/com/mindsense/app/MindSenseApplication.kt`
  - entry point do Hilt
- `app/src/main/java/com/mindsense/app/MainActivity.kt`
  - host Compose raiz com tema e `AppNavHost`

### DI
- `app/src/main/java/com/mindsense/app/di/AppModule.kt`
  - bindings de interfaces de repositório para implementações fake
- `app/src/main/java/com/mindsense/app/di/CoroutineModule.kt`
  - provider central de dispatchers
- `app/src/main/java/com/mindsense/core/network/di/NetworkModule.kt`
  - `Json`, `OkHttpClient`, `Retrofit` e `MindSenseApi`
- `app/src/main/java/com/mindsense/core/database/di/DatabaseModule.kt`
  - Room database e DAOs
- `app/src/main/java/com/mindsense/core/datastore/di/DataStoreModule.kt`
  - `DataStore<Preferences>`

### Core técnico
- `app/src/main/java/com/mindsense/core/ui/state/UiState.kt`
  - estado padrão de tela com `Loading`, `Content`, `Empty` e `Error`
- `app/src/main/java/com/mindsense/core/ui/scaffold/MindSenseScaffold.kt`
  - scaffold base com top bar e bottom bar opcionais
- `app/src/main/java/com/mindsense/core/ui/components/MindSenseTopBar.kt`
  - top app bar padrão
- `app/src/main/java/com/mindsense/core/ui/components/MindSenseBottomBar.kt`
  - navegação inferior principal
- `app/src/main/java/com/mindsense/core/ui/components/FeaturePlaceholderContent.kt`
  - placeholder reutilizável para features bootstrapadas
- `app/src/main/java/com/mindsense/core/ui/feedback/LoadingState.kt`
- `app/src/main/java/com/mindsense/core/ui/feedback/EmptyState.kt`
- `app/src/main/java/com/mindsense/core/ui/feedback/ErrorState.kt`
  - estados compartilhados de feedback

### Design system inicial
- `app/src/main/java/com/mindsense/core/designsystem/theme/Color.kt`
- `app/src/main/java/com/mindsense/core/designsystem/theme/Typography.kt`
- `app/src/main/java/com/mindsense/core/designsystem/theme/Shape.kt`
- `app/src/main/java/com/mindsense/core/designsystem/theme/Spacing.kt`
- `app/src/main/java/com/mindsense/core/designsystem/theme/Elevation.kt`
- `app/src/main/java/com/mindsense/core/designsystem/theme/Theme.kt`
  - base visual escura derivada do protótipo JS
- `app/src/main/java/com/mindsense/core/designsystem/components/PrimaryButton.kt`
- `app/src/main/java/com/mindsense/core/designsystem/components/SecondaryButton.kt`
- `app/src/main/java/com/mindsense/core/designsystem/components/AppCard.kt`
  - primeiros componentes reutilizáveis

### Navegação
- `app/src/main/java/com/mindsense/core/navigation/AppDestination.kt`
  - destinos e rotas do app
- `app/src/main/java/com/mindsense/core/navigation/AppNavHost.kt`
  - `NavHost` raiz com rotas principais e secundárias

### Dados e persistência
- `app/src/main/java/com/mindsense/core/network/api/MindSenseApi.kt`
  - contrato Retrofit base
- `app/src/main/java/com/mindsense/core/network/interceptor/AuthInterceptor.kt`
  - interceptor inicial
- `app/src/main/java/com/mindsense/core/database/MindSenseDatabase.kt`
  - Room database
- `app/src/main/java/com/mindsense/core/database/entity/*`
  - entidades de coleção, artigo e notificação
- `app/src/main/java/com/mindsense/core/database/dao/*`
  - DAOs mínimos
- `app/src/main/java/com/mindsense/core/datastore/*`
  - preferência de onboarding, sessão e nome do usuário

### Camada domain/data
- `app/src/main/java/com/mindsense/domain/model/BootstrapModels.kt`
  - modelos de domínio iniciais
- `app/src/main/java/com/mindsense/domain/repository/BootstrapRepositories.kt`
  - interfaces de repositório
- `app/src/main/java/com/mindsense/data/dto/BootstrapDtos.kt`
  - DTOs base para futura integração
- `app/src/main/java/com/mindsense/data/repository/BootstrapRepositoryImpl.kt`
  - implementações fake conectadas ao DI
- `app/src/main/java/com/mindsense/feature/shared/fake/FakeSeedData.kt`
  - seed central com dados do protótipo

### Features bootstrapadas
- `feature/onboarding`
- `feature/auth`
- `feature/dashboard`
- `feature/history`
- `feature/insights`
- `feature/content`
- `feature/notifications`
- `feature/profile`
- `feature/sync`
- `feature/assessment`

Cada uma já tem:
- screen
- viewmodel
- ui state
- action/event

## O que foi adaptado
- A navegação foi reorganizada para Android Compose com bottom bar persistente só nas áreas principais.
- Telas secundárias como detalhe, recomendações, artigo e resultado do assessment já têm rotas próprias, mas ainda como placeholder estrutural.
- Repositórios fake foram usados no lugar de API real para preservar o comportamento incremental pedido.
- `DataStore` e `Room` entraram desde o bootstrap, mesmo antes das telas finais.

## O que ainda depende de integração real
- backend Django real
- autenticação real
- payloads e DTOs finais
- política real de cache e sincronização
- integração com wearable/Wear OS
- UI final das telas baseada fielmente no protótipo
- design system completo da Fase 4

## Limitações desta entrega
- O ambiente desta sessão não possui `gradle` nem `kotlinc`, então não foi possível executar build local.
- A estrutura foi preparada para abrir no Android Studio e seguir para Fase 4 e Fase 5.

## Próximo passo
- Fase 4: traduzir o design system do JS para Compose, substituindo placeholders por componentes base visuais do produto.
