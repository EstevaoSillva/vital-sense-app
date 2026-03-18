package com.mindsense.data.repository

import com.mindsense.core.common.result.AppResult
import com.mindsense.core.datastore.OnboardingPreferencesDataSource
import com.mindsense.core.datastore.SessionPreferencesDataSource
import com.mindsense.core.datastore.UserPreferencesDataSource
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
import com.mindsense.domain.repository.AssessmentRepository
import com.mindsense.domain.repository.AuthRepository
import com.mindsense.domain.repository.ContentRepository
import com.mindsense.domain.repository.DashboardRepository
import com.mindsense.domain.repository.HistoryRepository
import com.mindsense.domain.repository.InsightsRepository
import com.mindsense.domain.repository.NotificationRepository
import com.mindsense.domain.repository.ProfileRepository
import com.mindsense.domain.repository.SyncRepository
import com.mindsense.feature.shared.fake.FakeSeedData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val onboardingPreferencesDataSource: OnboardingPreferencesDataSource,
    private val sessionPreferencesDataSource: SessionPreferencesDataSource,
    private val userPreferencesDataSource: UserPreferencesDataSource,
) : AuthRepository {
    override fun observeOnboardingCompleted(): Flow<Boolean> =
        onboardingPreferencesDataSource.observeCompleted()

    override fun observeLoggedIn(): Flow<Boolean> =
        sessionPreferencesDataSource.observeLoggedIn()

    override fun observeUserName(): Flow<String?> =
        userPreferencesDataSource.observeUserName()

    override suspend fun setOnboardingCompleted(completed: Boolean) {
        onboardingPreferencesDataSource.setCompleted(completed)
    }

    override suspend fun login(email: String, password: String): AppResult<Unit> {
        sessionPreferencesDataSource.setLoggedIn(true)
        userPreferencesDataSource.setUserName("Lucas")
        return AppResult.Success(Unit)
    }

    override suspend fun logout() {
        sessionPreferencesDataSource.setLoggedIn(false)
    }
}

@Singleton
class DashboardRepositoryImpl @Inject constructor() : DashboardRepository {
    override suspend fun getDashboardSnapshot(): StressSnapshot = FakeSeedData.dashboardSnapshot
}

@Singleton
class HistoryRepositoryImpl @Inject constructor() : HistoryRepository {
    override suspend fun getCollections(): List<CollectionSession> = FakeSeedData.collections
    override suspend fun getCollectionDetail(id: String): CollectionDetail? {
        val session = FakeSeedData.collections.firstOrNull { it.id == id } ?: return null
        return FakeSeedData.collectionDetail.copy(session = session)
    }
}

@Singleton
class InsightsRepositoryImpl @Inject constructor() : InsightsRepository {
    override suspend fun getInsightSummary(): InsightSummary = FakeSeedData.insightSummary
    override suspend fun getRecommendations(): List<Recommendation> = FakeSeedData.recommendations
}

@Singleton
class ProfileRepositoryImpl @Inject constructor() : ProfileRepository {
    override suspend fun getProfile(): UserProfile = FakeSeedData.profile
}

@Singleton
class ContentRepositoryImpl @Inject constructor() : ContentRepository {
    override suspend fun getFeaturedArticles(): List<ArticleSummary> = FakeSeedData.articles
    override suspend fun getArticleDetail(id: String): ArticleDetail {
        val article = FakeSeedData.articles.firstOrNull { it.id == id }
        return if (article != null) {
            FakeSeedData.articleDetail.copy(
                id = article.id,
                title = article.title,
                category = article.category,
            )
        } else {
            FakeSeedData.articleDetail
        }
    }
}

@Singleton
class AssessmentRepositoryImpl @Inject constructor() : AssessmentRepository {
    override suspend fun getQuestions(): List<AssessmentQuestion> = FakeSeedData.questions
    override suspend fun submitAnswers(answers: Map<String, Int>): AssessmentResult = FakeSeedData.assessmentResult
}

@Singleton
class SyncRepositoryImpl @Inject constructor() : SyncRepository {
    override suspend fun getSyncStatus(): WatchSyncStatus = FakeSeedData.syncStatus

    override suspend fun syncNow(): AppResult<WatchSyncStatus> {
        delay(1_500)
        return AppResult.Success(FakeSeedData.syncStatus.copy(syncing = false))
    }
}

@Singleton
class NotificationRepositoryImpl @Inject constructor() : NotificationRepository {
    override suspend fun getNotifications(): List<NotificationItem> = FakeSeedData.notifications
}
