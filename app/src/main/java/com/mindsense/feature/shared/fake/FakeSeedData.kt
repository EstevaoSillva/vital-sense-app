package com.mindsense.feature.shared.fake

import com.mindsense.domain.model.ArticleDetail
import com.mindsense.domain.model.ArticleSection
import com.mindsense.domain.model.ArticleSummary
import com.mindsense.domain.model.AssessmentQuestion
import com.mindsense.domain.model.AssessmentResult
import com.mindsense.domain.model.CollectionDetail
import com.mindsense.domain.model.CollectionSession
import com.mindsense.domain.model.InsightSummary
import com.mindsense.domain.model.NotificationItem
import com.mindsense.domain.model.Recommendation
import com.mindsense.domain.model.StressSnapshot
import com.mindsense.domain.model.UserProfile
import com.mindsense.domain.model.WatchSyncStatus

object FakeSeedData {
    val dashboardSnapshot = StressSnapshot(
        score = 38,
        label = "Normal",
        deviceName = "Pixel Watch 2",
        lastSyncLabel = "Hoje, 14:32",
    )

    val collections = listOf(
        CollectionSession("1", "Sessão da Tarde", "Hoje, 14:32", "45 min", 38, "Normal", "Excelente", "Pixel Watch 2"),
        CollectionSession("2", "Sessão da Manhã", "Hoje, 09:15", "1h 20min", 52, "Elevado", "Boa", "Pixel Watch 2"),
    )

    val collectionDetail = CollectionDetail(
        session = collections.first(),
        heartRatePoints = listOf(72, 78, 85, 80, 76, 74, 70),
        stressPoints = listOf(35, 40, 55, 62, 58, 50, 38),
        sensors = listOf("PPG", "Acelerômetro", "Temperatura"),
        observation = "Recuperação dentro do esperado para o período.",
        recommendation = "Manter pausas regulares antes de períodos de maior carga.",
    )

    val insightSummary = InsightSummary(
        weeklyStress = listOf(52, 48, 55, 45, 42, 40, 38),
        monthlyStress = listOf(60, 55, 58, 52, 50, 48, 45, 42, 40, 38),
        burnoutRiskTrend = listOf(35, 33, 30, 28, 25, 22),
        criticalFactors = listOf("Sono fragmentado", "Sedentarismo prolongado"),
        trendLabel = "Melhorando",
    )

    val recommendations = listOf(
        Recommendation("1", "Faça uma pausa de 15 minutos", "Uma caminhada leve pode ajudar.", "Stress acima de 50 por mais de 2 horas", "Alta"),
        Recommendation("2", "Sessão de respiração guiada", "4 minutos para reduzir a FC.", "FC média elevada nas últimas 3 horas", "Média"),
    )

    val articles = listOf(
        ArticleSummary("1", "5 Técnicas de Respiração para Crises", "Bem-estar", 5),
        ArticleSummary("2", "Como Identificar os Primeiros Sinais de Burnout", "Prevenção", 8),
    )

    val articleDetail = ArticleDetail(
        id = "1",
        title = "5 Técnicas de Respiração para Crises",
        category = "Bem-estar",
        summary = "Respiração controlada reduz a resposta de stress do corpo.",
        author = "Dra. Ana Beatriz Silva",
        sections = listOf(
            ArticleSection("Respiração 4-7-8", "Inspire por 4, segure por 7 e expire por 8."),
            ArticleSection("Respiração diafragmática", "Expanda o abdômen, não o peito."),
        ),
        watchSummary = "Resp. 4-7-8: Inspire 4s, segure 7s, expire 8s.",
    )

    val notifications = listOf(
        NotificationItem("1", "Stress", "Stress elevado detectado", "Considere uma pausa.", "14:45 — Hoje"),
        NotificationItem("2", "Sync", "Sincronização concluída", "142kb recebidos do relógio.", "14:32 — Hoje"),
    )

    val profile = UserProfile(
        name = "Lucas Oliveira",
        email = "lucas.oliveira@empresa.com",
        jobTitle = "Analista de Dados Sênior",
        company = "TechCorp Brasil",
        workSchedule = "8h às 17h — Seg a Sex",
    )

    val questions = listOf(
        AssessmentQuestion("1", "Exaustão emocional", "Com que frequência você se sente emocionalmente esgotado(a)?", listOf("Nunca", "Raramente", "Às vezes", "Frequentemente", "Sempre")),
        AssessmentQuestion("2", "Sono e recuperação", "Você tem dificuldade para dormir por causa do trabalho?", listOf("Nunca", "Raramente", "Às vezes", "Frequentemente", "Sempre")),
    )

    val assessmentResult = AssessmentResult(
        stressScore = 38,
        burnoutScore = 25,
        riskLabel = "Baixo",
        interpretation = "Os dados sugerem um bom nível de recuperação.",
        mainRecommendation = "Adicione 10 minutos de respiração guiada antes de dormir.",
        attentionFactors = listOf("Sono levemente fragmentado", "Stress em queda"),
    )

    val syncStatus = WatchSyncStatus(
        deviceName = "Pixel Watch 2",
        isConnected = true,
        batteryPercent = 72,
        lastSyncLabel = "Hoje, 14:32",
        syncing = false,
    )
}
