package com.mindsense.app.di

import com.mindsense.data.repository.AssessmentRepositoryImpl
import com.mindsense.data.repository.AuthRepositoryImpl
import com.mindsense.data.repository.ContentRepositoryImpl
import com.mindsense.data.repository.DashboardRepositoryImpl
import com.mindsense.data.repository.HistoryRepositoryImpl
import com.mindsense.data.repository.InsightsRepositoryImpl
import com.mindsense.data.repository.NotificationRepositoryImpl
import com.mindsense.data.repository.ProfileRepositoryImpl
import com.mindsense.data.repository.SyncRepositoryImpl
import com.mindsense.domain.repository.AssessmentRepository
import com.mindsense.domain.repository.AuthRepository
import com.mindsense.domain.repository.ContentRepository
import com.mindsense.domain.repository.DashboardRepository
import com.mindsense.domain.repository.HistoryRepository
import com.mindsense.domain.repository.InsightsRepository
import com.mindsense.domain.repository.NotificationRepository
import com.mindsense.domain.repository.ProfileRepository
import com.mindsense.domain.repository.SyncRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindDashboardRepository(impl: DashboardRepositoryImpl): DashboardRepository

    @Binds
    @Singleton
    abstract fun bindHistoryRepository(impl: HistoryRepositoryImpl): HistoryRepository

    @Binds
    @Singleton
    abstract fun bindInsightsRepository(impl: InsightsRepositoryImpl): InsightsRepository

    @Binds
    @Singleton
    abstract fun bindProfileRepository(impl: ProfileRepositoryImpl): ProfileRepository

    @Binds
    @Singleton
    abstract fun bindContentRepository(impl: ContentRepositoryImpl): ContentRepository

    @Binds
    @Singleton
    abstract fun bindAssessmentRepository(impl: AssessmentRepositoryImpl): AssessmentRepository

    @Binds
    @Singleton
    abstract fun bindSyncRepository(impl: SyncRepositoryImpl): SyncRepository

    @Binds
    @Singleton
    abstract fun bindNotificationRepository(impl: NotificationRepositoryImpl): NotificationRepository
}
