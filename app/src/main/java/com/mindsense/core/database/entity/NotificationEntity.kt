package com.mindsense.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notifications")
data class NotificationEntity(
    @PrimaryKey val id: String,
    val category: String,
    val title: String,
    val description: String,
    val timestamp: String,
)
