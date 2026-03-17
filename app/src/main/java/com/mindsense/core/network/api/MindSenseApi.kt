package com.mindsense.core.network.api

import com.mindsense.data.dto.AssessmentResultDto
import com.mindsense.data.dto.ArticleDetailDto
import com.mindsense.data.dto.ArticleSummaryDto
import com.mindsense.data.dto.CollectionDetailDto
import com.mindsense.data.dto.CollectionSessionDto
import com.mindsense.data.dto.DashboardSnapshotDto
import com.mindsense.data.dto.InsightSummaryDto
import com.mindsense.data.dto.NotificationItemDto
import com.mindsense.data.dto.RecommendationDto
import com.mindsense.data.dto.UserProfileDto
import com.mindsense.data.dto.WatchSyncStatusDto
import retrofit2.http.GET
import retrofit2.http.Path

interface MindSenseApi {

    @GET("dashboard/summary")
    suspend fun getDashboardSummary(): DashboardSnapshotDto

    @GET("history/collections")
    suspend fun getCollections(): List<CollectionSessionDto>

    @GET("history/collections/{collectionId}")
    suspend fun getCollectionDetail(@Path("collectionId") collectionId: String): CollectionDetailDto

    @GET("insights")
    suspend fun getInsights(): InsightSummaryDto

    @GET("recommendations")
    suspend fun getRecommendations(): List<RecommendationDto>

    @GET("content/articles")
    suspend fun getArticles(): List<ArticleSummaryDto>

    @GET("content/articles/{articleId}")
    suspend fun getArticle(@Path("articleId") articleId: String): ArticleDetailDto

    @GET("notifications")
    suspend fun getNotifications(): List<NotificationItemDto>

    @GET("profile")
    suspend fun getProfile(): UserProfileDto

    @GET("assessment/result")
    suspend fun getAssessmentResult(): AssessmentResultDto

    @GET("sync/status")
    suspend fun getSyncStatus(): WatchSyncStatusDto
}
