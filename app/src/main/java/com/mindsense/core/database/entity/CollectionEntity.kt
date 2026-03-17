package com.mindsense.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "collections")
data class CollectionEntity(
    @PrimaryKey val id: String,
    val title: String,
    val timestamp: String,
    val durationLabel: String,
    val score: Int,
    val label: String,
    val quality: String,
    val deviceName: String,
)
