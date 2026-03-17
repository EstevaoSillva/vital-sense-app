package com.mindsense.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class DashboardSnapshotDto(
    val score: Int,
    val label: String,
    val deviceName: String,
    val lastSyncLabel: String,
)

@Serializable
data class CollectionSessionDto(
    val id: String,
    val title: String,
    val timestamp: String,
    val durationLabel: String,
    val score: Int,
    val label: String,
    val quality: String,
    val deviceName: String,
)

@Serializable
data class CollectionDetailDto(
    val session: CollectionSessionDto,
    val heartRatePoints: List<Int>,
    val stressPoints: List<Int>,
    val sensors: List<String>,
    val observation: String,
    val recommendation: String,
)

@Serializable
data class InsightSummaryDto(
    val weeklyStress: List<Int>,
    val monthlyStress: List<Int>,
    val burnoutRiskTrend: List<Int>,
    val criticalFactors: List<String>,
    val trendLabel: String,
)

@Serializable
data class RecommendationDto(
    val id: String,
    val title: String,
    val description: String,
    val reason: String,
    val priority: String,
)

@Serializable
data class ArticleSummaryDto(
    val id: String,
    val title: String,
    val category: String,
    val readTimeMinutes: Int,
)

@Serializable
data class ArticleDetailDto(
    val id: String,
    val title: String,
    val category: String,
    val summary: String,
    val author: String,
    val sections: List<ArticleSectionDto>,
    val watchSummary: String,
)

@Serializable
data class ArticleSectionDto(
    val heading: String,
    val body: String,
)

@Serializable
data class NotificationItemDto(
    val id: String,
    val category: String,
    val title: String,
    val description: String,
    val timestamp: String,
)

@Serializable
data class UserProfileDto(
    val name: String,
    val email: String,
    val jobTitle: String,
    val company: String,
    val workSchedule: String,
)

@Serializable
data class WatchSyncStatusDto(
    val deviceName: String,
    val isConnected: Boolean,
    val batteryPercent: Int,
    val lastSyncLabel: String,
    val syncing: Boolean,
)

@Serializable
data class AssessmentResultDto(
    val stressScore: Int,
    val burnoutScore: Int,
    val riskLabel: String,
    val interpretation: String,
    val mainRecommendation: String,
    val attentionFactors: List<String>,
)
