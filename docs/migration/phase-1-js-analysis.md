# Fase 1 - Análise do diretório JS (`mind-sense`)

## Escopo analisado
- Origem: `/home/estevaosilva/repository/mind-sense`
- Stack atual: Vite + React + TypeScript + React Router + Tailwind + Framer Motion
- Ponto de entrada: `src/main.tsx`
- Rotas: `src/App.tsx`
- Observação: o app está majoritariamente em estado prototipado, com dados mockados e estados locais.

## Visão geral
- Produto: app de biometria, stress e risco de burnout com integração simulada a smartwatch Wear OS.
- Estrutura principal:
  - onboarding
  - login
  - dashboard
  - histórico de coletas
  - detalhe de coleta
  - insights
  - recomendações
  - exploração de conteúdo
  - leitura de artigo
  - sincronização com relógio
  - notificações
  - perfil
  - assessment
  - resultado consolidado

## Lista de telas

| Tela | Rota | Arquivo | Papel no fluxo |
|---|---|---|---|
| Onboarding | `/` | `src/pages/Onboarding.tsx` | Entrada inicial e apresentação do produto |
| Login | `/login` | `src/pages/Login.tsx` | Autenticação simulada |
| Dashboard | `/dashboard` | `src/pages/Dashboard.tsx` | Resumo principal de sinais, riscos e atalhos |
| History | `/history` | `src/pages/History.tsx` | Lista de sessões/coletas biométricas |
| Collection Detail | `/collection/:id` | `src/pages/CollectionDetail.tsx` | Detalhe de uma coleta/sessão |
| Insights | `/insights` | `src/pages/Insights.tsx` | Tendências e fatores críticos |
| Recommendations | `/recommendations` | `src/pages/Recommendations.tsx` | Lista de recomendações personalizadas |
| Explore | `/explore` | `src/pages/Explore.tsx` | Busca e descoberta de conteúdo |
| Article | `/article/:id` | `src/pages/Article.tsx` | Leitura de conteúdo e envio de resumo ao relógio |
| Sync Watch | `/sync` | `src/pages/SyncWatch.tsx` | Estado do wearable, sincronização e permissões |
| Notifications | `/notifications` | `src/pages/Notifications.tsx` | Feed de alertas e eventos |
| Profile | `/profile` | `src/pages/Profile.tsx` | Dados do usuário e configurações |
| Assessment | `/assessment` | `src/pages/Assessment.tsx` | Questionário passo a passo |
| Assessment Result | `/assessment-result` | `src/pages/AssessmentResult.tsx` | Resultado consolidado do assessment |
| Not Found | `*` | `src/pages/NotFound.tsx` | Fallback de rota inválida |

## Mapa de navegação

### Fluxo principal
`Onboarding -> Login -> Dashboard`

### Bottom navigation
- `/dashboard`
- `/history`
- `/insights`
- `/explore`
- `/profile`

### Navegação a partir do dashboard
- Dashboard -> Sync
- Dashboard -> Notifications
- Dashboard -> Collection Detail
- Dashboard -> Insights
- Dashboard -> Recommendations
- Dashboard -> Assessment

### Navegação de conteúdo
- Explore -> Article
- Article -> Explore
- Assessment Result -> Explore

### Navegação de assessment
- Dashboard -> Assessment
- Notifications -> sem navegação ligada, mas existe CTA semântica para assessment
- Assessment -> Assessment Result
- Assessment Result -> Insights
- Assessment Result -> Assessment
- Assessment Result -> Dashboard

### Navegação de histórico
- History -> Collection Detail

### Navegação de perfil
- Profile -> Sync
- Profile -> Onboarding (logout)

### Observações
- `Index.tsx` é placeholder Lovable e não participa da navegação real.
- Há muitos componentes `src/components/ui/*` gerados por shadcn/Radix que não são usados nas telas atuais.

## Shell, layout e navegação compartilhada

### AppShell
- Arquivo: `src/components/layout/AppShell.tsx`
- Responsável por:
  - limitar largura em `max-w-lg`
  - aplicar fundo escuro
  - reservar espaço para bottom nav

### BottomNav
- Arquivo: `src/components/layout/BottomNav.tsx`
- Tabs:
  - Início
  - Histórico
  - Insights
  - Explorar
  - Perfil
- Comportamento:
  - indicador animado da tab ativa
  - cor primária para item ativo
  - barra fixa com blur

### PageTransition
- Arquivo: `src/components/shared/PageTransition.tsx`
- Entrada/saída vertical com fade usando Framer Motion.
- Deve virar um padrão opcional de transição no Android, sem excessos.

## Componentes reutilizáveis reais

### Componentes de domínio visual usados pelas telas
- `MetricCard`
  - card de métrica com label, valor grande, subtítulo e status dot
- `StressGauge`
  - gauge semicircular com score, label textual e cor por faixa
- `Sparkline`
  - mini gráfico SVG de linha/área
- `LoadingState`
  - skeleton + barra animada + mensagem
- `EmptyState`
  - ícone, título, descrição e CTA opcional
- `ErrorState`
  - estado de erro com retry opcional
- `AppShell`
- `BottomNav`
- `PageTransition`

### Componentes utilitários gerados, mas sem uso real no app
- pacote inteiro `src/components/ui/*`
- inclui dialog, sheet, drawer, tabs, form, toast, select, calendar etc.
- hoje isso é infraestrutura importada/gerada, não comportamento implementado do produto.

## Estados de UI encontrados

### Loading
- Existe componente genérico `LoadingState`, mas não está ligado a nenhuma tela funcional atual.
- `SyncWatch` tem loading implícito no botão durante sincronização (`syncing` com spinner e texto dinâmico).

### Error
- `SyncWatch` possui ramificação de erro com `ErrorState`.
- Estado é local (`showError`) e hoje não há ação visível na UI que o ative.

### Empty
- `History` possui estado vazio com `EmptyState`.
- `SyncWatch` possui estado desconectado com `EmptyState`.
- Ambos dependem de flags locais (`showEmpty`, `showDisconnected`) sem mecanismo exposto para alternância.

### Success
- Dashboard, Insights, Detail e Result trabalham quase sempre em estado de sucesso mockado.
- `Article` possui confirmação de sucesso local ao enviar resumo para relógio (`sentToWatch = true`).

### Formulário
- `Login`
  - email
  - senha
  - alternância mostrar/ocultar senha
- `Assessment`
  - formulário wizard de 7 perguntas com escala de 1 a 5

### Modal
- Não há modal funcional implementado nas telas analisadas.
- Existem componentes de modal/sheet/drawer gerados em `ui/`, mas sem uso.

### Bottom sheet
- Não há bottom sheet em uso.

### Tabs e filtros
- Bottom navigation no shell principal
- Chips de filtro em:
  - History
  - Recommendations
  - Notifications
- Os filtros alteram estado visual; só Notifications realmente filtra a lista.

## Comportamentos por tela

### Onboarding
- 3 slides estáticos
- botão `Pular` leva para login
- botão principal avança slide
- no último slide navega para login
- indicadores de página animados

### Login
- inputs controlados por `useState`
- toggle de visibilidade de senha
- submit navega direto para dashboard
- botão biometria também navega direto para dashboard
- sem validação, sem erro, sem autenticação real

### Dashboard
- header com saudação e nome fixo
- botão para sync
- botão para notifications com badge vermelho
- card principal clicável abre detalhe da coleta
- cards secundários abrem insights e sync
- tendência de 7 dias abre insights
- recomendação do dia abre recommendations
- CTA assessment abre assessment
- cards HR e HRV são apenas visuais

### History
- filtros por período com feedback visual
- lista de coletas mockadas
- clique em item abre detalhe
- estado vazio existe mas não é acionado por filtro nem por ausência real de dados

### Collection Detail
- voltar usando histórico de navegação
- botões bookmark/share sem ação
- gauge de stress
- gráfico de frequência cardíaca
- gráfico de stress na sessão
- metadados do sinal/sensores/dispositivo
- blocos textuais de observação e recomendação
- CTA final "Enviar resumo ao relógio" sem handler

### Insights
- cards de tendência semanal/mensal/burnout
- comparação semanal
- chips de fatores críticos
- resumo final de tendência
- sem filtros, sem drill-down, sem navegação ativa

### Recommendations
- filtros visuais `Hoje`, `Semana`, `Críticas`
- lista mockada com prioridade, categoria e motivo
- nenhum item abre detalhe
- filtro não altera dados atualmente

### Explore
- campo de busca controlado por estado local
- chips de tópicos rápidos sem ação
- carrossel horizontal "Em destaque"
- listas "Para você" e "Mais lidos"
- clique em card abre article
- busca não filtra lista

### Article
- voltar
- bookmark/share sem ação
- conteúdo textual estático
- card de dica contextual
- preview do relógio
- CTA envia resumo ao relógio e muda para estado de sucesso local
- card relacionado leva para explore, não para artigo relacionado específico

### Sync Watch
- voltar
- estado padrão conectado
- botão de sincronização:
  - entra em loading por 3 segundos
  - retorna ao estado normal
- botões "Testar conexão" e "Configurar dados do relógio" sem ação
- toggles visuais em "Exibido no relógio", mas não são interativos
- estados especiais:
  - erro
  - relógio desconectado
- ambos existem no código, mas não têm trigger exposto

### Notifications
- chips de categoria
- lista filtrada por categoria
- cards informativos apenas visuais
- sem ação ao tocar em notificação

### Profile
- card do usuário
- informações profissionais
- lista de settings
- somente "Dispositivos conectados" navega para sync
- demais itens sem implementação
- logout navega para onboarding

### Assessment
- wizard de 7 perguntas
- progresso linear no topo
- categoria por pergunta
- respostas 1..5 com seleção visual
- botão próximo habilita apenas quando há resposta
- último passo envia para resultado
- sem persistência, sem score real calculado na tela

### Assessment Result
- cards de stress e burnout
- classificação de risco
- interpretação textual
- fatores de atenção
- recomendação principal
- CTAs:
  - insights
  - novo assessment
  - explorar conteúdo
  - enviar resumo ao relógio sem ação
  - voltar ao início

### NotFound
- fallback simples em inglês
- usa link direto para `/`
- desalinhado visualmente em relação ao restante do produto

## Estrutura visual por tela

### Padrões recorrentes
- header simples com título forte e subtítulo opcional
- cards com borda discreta e alto arredondamento
- listas de cards empilhados
- botões principais cheios e secundários em superfície escura
- chips arredondados para filtros e tags
- uso intenso de ícones Lucide
- layout mobile-first em coluna única com largura limitada

### Elementos por área
- Onboarding:
  - hero icon circular
  - título central
  - descrição
  - pager indicator
  - CTA full width
- Auth:
  - branding central
  - dois campos
  - ações primária e biométrica
- Dashboard:
  - top header
  - status de relógio
  - gauge principal
  - grid 2 colunas
  - sparklines
  - recommendation card
  - CTA card
  - bottom navigation
- History:
  - filtros horizontais
  - lista de cards com score/status/qualidade/dispositivo
- Detail:
  - header de retorno
  - ações secundárias
  - gauge
  - gráficos
  - metadata list
  - blocos informativos
  - CTA final
- Explore/Article:
  - busca
  - chips
  - listas horizontais e verticais
  - leitura longa
  - preview Wear OS
- Assessment:
  - progress bar
  - pergunta única por vez
  - respostas em lista
  - CTA fixado no rodapé

## Dependências funcionais

### Dados mockados
- Todas as telas de negócio usam arrays e valores hardcoded no próprio arquivo.
- Exemplos:
  - séries do dashboard
  - coleções do histórico
  - recomendações
  - artigos
  - notificações
  - perguntas do assessment

### API / rede
- Não há chamadas HTTP reais.
- Não há `fetch`, `axios`, `useQuery` ou `useMutation` nas telas.
- `QueryClientProvider` existe em `App.tsx`, mas React Query não está sendo usado funcionalmente.

### Estado
- Predomínio de `useState` local por tela.
- Não há store global.
- Não há Context de domínio.
- Não há cache de dados, persistência nem sessão real.

### Props e dependências de componente
- Componentes compartilhados recebem props simples e síncronas.
- Não há composição complexa de estado.

### Hooks
- Hooks próprios presentes:
  - `hooks/use-mobile.tsx`
  - `hooks/use-toast.ts`
- Não participam do fluxo central das telas analisadas.

## Design system e tokens visuais

### Cores principais
- background: preto
- foreground: quase branco
- card: preto muito escuro
- primary: azul vivo
- success/accent: verde neon
- destructive/warning: magenta/rosa forte
- muted foreground: cinza médio

### Tipografia
- display: `Plus Jakarta Sans`
- body: `Inter`
- mono numérica: `JetBrains Mono`
- números de métricas usam fonte mono com `tabular-nums`

### Espaçamentos
- pads horizontais frequentes de `px-5` ou `px-6`
- cards com `p-4` a `p-6`
- espaçamento vertical consistente por blocos curtos

### Bordas e formas
- radius base: `1rem`
- cards geralmente `rounded-3xl`
- botões principais `rounded-2xl`
- chips `rounded-full`

### Elevação e superfície
- `card-surface`:
  - fundo escuro
  - borda branca translúcida
  - sombra forte difusa
- bottom nav com blur

### Efeitos
- glow azul/verde/magenta
- shimmer para loading
- pulse glow para status
- transições de página e taps com scale

### Ícones
- `lucide-react`
- sem ilustrações complexas além de ícones e um emoji na tela de resultado

## Fluxos principais do usuário

### Onboarding
- conhecer proposta do produto
- pular ou avançar até login

### Login
- entrar com email/senha ou biometria
- ambos levam direto ao dashboard

### Dashboard
- consultar estado atual
- ir para sync, notifications, insights, recommendations, assessment ou detalhe da coleta

### Histórico
- revisar sessões anteriores
- abrir detalhe de sessão

### Insights
- acompanhar tendência semanal/mensal
- consumir leitura interpretativa

### Perfil
- revisar dados do usuário
- acessar dispositivos conectados

### Sincronização
- ver status do relógio
- sincronizar manualmente
- revisar permissões e sensores

### Conteúdo e pesquisa
- explorar temas
- abrir artigo
- enviar resumo ao relógio

### Notificações
- filtrar alertas por categoria

### Assessment
- responder questionário
- visualizar resultado consolidado

## Itens incompletos, mockados ou apenas visuais

### Claramente mockados
- autenticação
- dados biométricos
- recomendações
- insights
- notificações
- conteúdos
- perfil
- resultados de assessment

### Estruturas existentes, mas não conectadas
- `LoadingState` não usado nas telas
- `showEmpty` em History sem trigger de UX
- `showError` e `showDisconnected` em Sync sem trigger de UX
- filtros de Recommendations não filtram
- busca de Explore não busca
- quick topics sem ação
- cards de notificações sem navegação
- share/bookmark em detail e article sem ação
- botões "Testar conexão", "Configurar dados do relógio", "Enviar resumo ao relógio" em várias telas sem implementação
- toggles da tela de sync são apenas decorativos

### Código morto ou residual
- `src/pages/Index.tsx` é placeholder Lovable e deveria ser removido/ignorado na migração
- grande volume de componentes shadcn/Radix não usados pela UI atual
- React Query está preparado, mas o produto não usa consultas reais

## Riscos de migração

### Risco 1 - intenção funcional maior que implementação real
- Muitas telas expressam um fluxo de produto crível, mas a implementação atual é superficial.
- Na migração Android será necessário distinguir:
  - comportamento real existente
  - intenção de UX inferida

### Risco 2 - estados não exercitados
- Error/empty/loading existem no código, porém sem caminho real de execução em boa parte das telas.
- Será preciso decidir quando esses estados devem aparecer no app Android.

### Risco 3 - fidelidade web vs usabilidade Android
- O protótipo foi desenhado em padrão mobile web.
- Alguns padrões devem ser adaptados:
  - botão voltar textual
  - blur de bottom nav
  - listas horizontais com gesto
  - animações frequentes de scale

### Risco 4 - ausência de modelo de dados
- Não há contratos de API nem entidades consistentes.
- O mesmo conceito aparece em formatos implícitos diferentes entre telas.

### Risco 5 - navegação implícita
- Algumas notificações e CTAs sugerem destinos, mas não estão ligados.
- A arquitetura Android precisará explicitar rotas e parâmetros.

## Sugestões de adaptação para Android Compose

### Navegação
- `Onboarding`, `Login`, `Assessment` e detalhes fora do shell com `Scaffold` sem bottom bar.
- `Dashboard`, `History`, `Insights`, `Explore`, `Profile` dentro de um root com bottom navigation persistente.

### Estado
- Converter cada tela para `UiState` explícito:
  - `Loading`
  - `Content`
  - `Empty`
  - `Error`
- Mesmo quando o protótipo não exercita todos os estados.

### Componentização
- Traduzir para Compose:
  - `StressGauge`
  - `Sparkline`
  - `MetricCard`
  - `StatusChip`
  - `EmptyState`
  - `ErrorState`
  - `LoadingState`
  - `MindSenseBottomBar`

### Dados
- Iniciar com fake repositories por feature.
- Modelos mínimos sugeridos:
  - `UserProfile`
  - `StressSnapshot`
  - `CollectionSession`
  - `InsightSummary`
  - `Recommendation`
  - `ArticleSummary`
  - `ArticleDetail`
  - `NotificationItem`
  - `AssessmentQuestion`
  - `AssessmentResult`
  - `WatchSyncStatus`

### UX Android
- Usar top app bar nativa com `navigationIcon` em vez de botões de texto `← Voltar`
- Preservar visual escuro, cards altos e acentos neon
- Manter bottom nav para as 5 áreas centrais
- Adaptar gestos horizontais com `LazyRow`
- Evitar excesso de animação em toda interação; priorizar transições de navegação e estados

### Integração futura
- Preparar abstrações para:
  - Retrofit API de backend Django
  - Room para cache de coletas, artigos e notificações
  - DataStore para sessão, preferências e flags de onboarding
  - integração futura com Wear OS / Health Services

## Resumo executivo
- O diretório JS representa um protótipo mobile-first consistente visualmente, mas com baixa profundidade de dados e regras de negócio.
- O núcleo funcional do produto está claro:
  - biometria e stress
  - risco de burnout
  - sincronização com wearable
  - conteúdo educativo
  - assessment recorrente
- Para a migração Android, a maior parte do trabalho será:
  - formalizar estados
  - consolidar modelos de dados
  - transformar intenções visuais em fluxos previsíveis
  - preservar identidade visual sem carregar os atalhos e limitações do protótipo web

## Próximo passo
- Fase 2: definir a arquitetura Android, árvore de pacotes, estratégia de navegação, estado, persistência e bootstrap inicial sem implementar telas ainda.
