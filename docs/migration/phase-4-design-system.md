# Fase 4 - Design system Compose

## Tokens traduzidos do JS
- fundo preto profundo: `Background`
- superfície principal de cards: `Surface`
- superfície secundária de inputs e navegação: `SurfaceAlt` / `SurfaceMuted`
- azul de destaque: `PrimaryBlue`
- verde de sucesso: `SuccessGreen`
- magenta de alerta/risco: `WarningMagenta`
- borda translúcida de card: `CardBorder`

## Tipografia
- títulos fortes e compactos com tracking negativo
- corpo neutro e legível
- números de métricas com `FontFamily.Monospace`

## Shapes
- botão: `20.dp`
- input: `18.dp`
- card: `24.dp`
- chip: arredondamento máximo

## Componentes criados
- `PrimaryButton`
- `SecondaryButton`
- `AppCard`
- `SectionHeader`
- `MetricCard`
- `StatusChip`
- `SearchBar`
- `InputField`
- `FilterChipGroup`
- `EmptyState`
- `ErrorState`
- `LoadingState`
- `MindSenseTopAppBar`
- `MindSenseBottomNavigation`
- `StressGauge`
- `SparklineChart`

## Adaptações em relação ao protótipo JS
- o glow foi reduzido a tokens de cor e elevação para evitar ruído excessivo no bootstrap
- a bottom nav foi convertida para um componente Compose próprio com destaque de item ativo
- `LoadingState`, `EmptyState` e `ErrorState` viraram componentes do design system, e `core/ui/feedback` agora só delega para eles
- o gauge foi convertido para `Canvas` com arco de 270 graus, equivalente visual ao componente web
- o sparkline foi convertido para `Canvas`, mantendo linha e preenchimento translúcido

## Pronto para a Fase 5
- base visual centralizada
- componentes reutilizáveis disponíveis para todas as features
- tokens coerentes com o inventário do Lovable
