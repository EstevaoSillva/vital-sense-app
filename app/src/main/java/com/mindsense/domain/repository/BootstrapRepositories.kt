package com.mindsense.domain.repository

import com.mindsense.core.common.result.AppResult
import com.mindsense.domain.model.ArticleDetail
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
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun observeOnboardingCompleted(): Flow<Boolean>
    fun observeLoggedIn(): Flow<Boolean>
    fun observeUserName(): Flow<String?>
    suspend fun setOnboardingCompleted(completed: Boolean)
    suspend fun login(email: String, password: String): AppResult<Unit>
    suspend fun logout()
}

interface DashboardRepository {
    suspend fun getDashboardSnapshot(): StressSnapshot
}

interface HistoryRepository {
    suspend fun getCollections(): List<CollectionSession>
    suspend fun getCollectionDetail(id: String): CollectionDetail?
}

interface InsightsRepository {
    suspend fun getInsightSummary(): InsightSummary
    suspend fun getRecommendations(): List<Recommendation>
}

interface ProfileRepository {
    suspend fun getProfile(): UserProfile
}

interface ContentRepository {
    suspend fun getFeaturedArticles(): List<ArticleSummary>
    suspend fun getArticleDetail(id: String): ArticleDetail
}

interface AssessmentRepository {
    suspend fun getQuestions(): List<AssessmentQuestion>
    suspend fun submitAnswers(answers: Map<String, Int>): AssessmentResult
}

interface SyncRepository {
    suspend fun getSyncStatus(): WatchSyncStatus
    suspend fun syncNow(): AppResult<WatchSyncStatus>
}

interface NotificationRepository {
    suspend fun getNotifications(): List<NotificationItem>
}
