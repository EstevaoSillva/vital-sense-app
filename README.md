# vital-sense-ai
MindSense AI Mobile App é o aplicativo Android responsável por exibir, analisar e interpretar dados de saúde mental coletados por dispositivos Wear OS, com foco na detecção precoce de estresse e burnout.
O app atua como o hub central de visualização e interação do usuário, integrando dados fisiológicos provenientes do smartwatch com análises avançadas realizadas pelo backend de machine learning (Burnout Core).

# Principais Funcionalidades

📊 Dashboard de Saúde Mental
Visualização do nível atual de estresse, risco de burnout e métricas fisiológicas como frequência cardíaca e HRV.

📈 Histórico de Coletas
Acompanhamento detalhado das sessões capturadas pelo wearable, com gráficos e evolução temporal.

🧠 Insights e Tendências
Análise da evolução do estresse e burnout ao longo do tempo, com interpretações automáticas.

📝 Assessment de Burnout
Questionário estruturado para avaliação psicológica complementar.

💡 Recomendações Inteligentes
Sugestões personalizadas baseadas nos níveis de risco detectados.

🔔 Alertas e Notificações
Notificações proativas para situações de risco elevado ou necessidade de intervenção.

📚 Conteúdo Educativo
Pesquisa e leitura de conteúdos sobre burnout, prevenção e saúde mental.

⌚ Integração com Wear OS
Sincronização com smartwatch para recebimento de dados e envio de resumos e alertas.

# Arquitetura

O aplicativo segue boas práticas modernas de desenvolvimento Android:

Arquitetura
MVVM + Clean-ish architecture

UI
Jetpack Compose + Kotlin

DI
Hilt

Rede
Retrofit + OkHttp

Estado assíncrono
Coroutines + StateFlow

Navegação
Navigation Compose

Persistência
DataStore + Room

# Integração com o Ecossistema MindSense AI

Este app faz parte de uma plataforma maior composta por:

📡 Wear OS App → coleta de dados fisiológicos
🤖 Burnout Core (Backend ML) → processamento, inferência e avaliação de risco
📱 Mobile App (este repositório) → visualização, insights e interação

# Objetivo

Fornecer ao usuário uma visão clara, contínua e acionável do seu estado de saúde mental, permitindo monitoramento, prevenção e intervenção precoce em casos de estresse e burnout.

# Diferenciais

* Integração de dados fisiológicos + avaliação psicológica
* Uso de machine learning para inferência de risco
* Monitoramento contínuo via wearable
* Foco em prevenção, não apenas diagnóstico
* Experiência centrada no usuário com feedbacks claros e úteis

# Estrutura

app/
 ├─ data/
 │   ├─ remote/
 │   │   ├─ api/
 │   │   ├─ dto/
 │   │   └─ service/
 │   ├─ local/
 │   │   ├─ db/
 │   │   ├─ dao/
 │   │   └─ datastore/
 │   ├─ mapper/
 │   └─ repository/
 │
 ├─ domain/
 │   ├─ model/
 │   ├─ repository/
 │   └─ usecase/
 │
 ├─ presentation/
 │   ├─ dashboard/
 │   ├─ history/
 │   ├─ insights/
 │   ├─ profile/
 │   ├─ assessment/
 │   ├─ shared/
 │   └─ navigation/
 │
 ├─ di/
 ├─ core/
 │   ├─ ui/
 │   ├─ common/
 │   ├─ network/
 │   └─ util/
 └─ MainActivity.kt


 
