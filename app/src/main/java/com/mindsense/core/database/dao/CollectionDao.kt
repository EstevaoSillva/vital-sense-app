package com.mindsense.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mindsense.core.database.entity.CollectionEntity

@Dao
interface CollectionDao {
    @Query("SELECT * FROM collections ORDER BY timestamp DESC")
    suspend fun getAll(): List<CollectionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(items: List<CollectionEntity>)
}
