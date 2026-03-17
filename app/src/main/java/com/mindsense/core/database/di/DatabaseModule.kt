package com.mindsense.core.database.di

import android.content.Context
import androidx.room.Room
import com.mindsense.core.database.MindSenseDatabase
import com.mindsense.core.database.dao.ArticleDao
import com.mindsense.core.database.dao.CollectionDao
import com.mindsense.core.database.dao.NotificationDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MindSenseDatabase =
        Room.databaseBuilder(
            context,
            MindSenseDatabase::class.java,
            "mind-sense.db",
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideCollectionDao(database: MindSenseDatabase): CollectionDao = database.collectionDao()

    @Provides
    fun provideArticleDao(database: MindSenseDatabase): ArticleDao = database.articleDao()

    @Provides
    fun provideNotificationDao(database: MindSenseDatabase): NotificationDao = database.notificationDao()
}
