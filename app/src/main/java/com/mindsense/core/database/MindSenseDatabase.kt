package com.mindsense.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mindsense.core.database.dao.ArticleDao
import com.mindsense.core.database.dao.CollectionDao
import com.mindsense.core.database.dao.NotificationDao
import com.mindsense.core.database.entity.ArticleEntity
import com.mindsense.core.database.entity.CollectionEntity
import com.mindsense.core.database.entity.NotificationEntity

@Database(
    entities = [CollectionEntity::class, ArticleEntity::class, NotificationEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class MindSenseDatabase : RoomDatabase() {
    abstract fun collectionDao(): CollectionDao
    abstract fun articleDao(): ArticleDao
    abstract fun notificationDao(): NotificationDao
}
