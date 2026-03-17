package com.mindsense.domain.model

data class UserProfile(
    val name: String,
    val email: String,
    val jobTitle: String,
    val company: String,
    val workSchedule: String,
)

data class StressSnapshot(
    val score: Int,
    val label: String,
    val deviceName: String,
    val lastSyncLabel: String,
)

data class CollectionSession(
    val id: String,
    val title: String,
    val timestamp: String,
    val durationLabel: String,
    val score: Int,
    val label: String,
    val quality: String,
    val deviceName: String,
)

data class CollectionDetail(
    val session: CollectionSession,
    val heartRatePoints: List<Int>,
    val stressPoints: List<Int>,
    val sensors: List<String>,
    val observation: String,
    val recommendation: String,
)

data class InsightSummary(
    val weeklyStress: List<Int>,
    val monthlyStress: List<Int>,
    val burnoutRiskTrend: List<Int>,
    val criticalFactors: List<String>,
    val trendLabel: String,
)

data class Recommendation(
    val id: String,
    val title: String,
    val description: String,
    val reason: String,
    val priority: String,
)

data class ArticleSummary(
    val id: String,
    val title: String,
    val category: String,
    val readTimeMinutes: Int,
)

data class ArticleSection(
    val heading: String,
    val body: String,
)

data class ArticleDetail(
    val id: String,
    val title: String,
    val category: String,
    val summary: String,
    val author: String,
    val sections: List<ArticleSection>,
    val watchSummary: String,
)

data class NotificationItem(
    val id: String,
    val category: String,
    val title: String,
    val description: String,
    val timestamp: String,
)

data class WatchSyncStatus(
    val deviceName: String,
    val isConnected: Boolean,
    val batteryPercent: Int,
    val lastSyncLabel: String,
    val syncing: Boolean,
)

data class AssessmentQuestion(
    val id: String,
    val category: String,
    val text: String,
    val scaleLabels: List<String>,
)

data class AssessmentResult(
    val stressScore: Int,
    val burnoutScore: Int,
    val riskLabel: String,
    val interpretation: String,
    val mainRecommendation: String,
    val attentionFactors: List<String>,
)
