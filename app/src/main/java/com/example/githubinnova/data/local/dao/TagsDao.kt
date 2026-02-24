package com.example.githubinnova.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubinnova.data.local.entity.TagEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TagsDao {

    @Query("SELECT * FROM tags WHERE repoOwner = :repoOwner AND repoName = :repoName ORDER BY tagName ASC")
    fun getTagsByRepo(repoOwner: String, repoName: String): Flow<List<TagEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTags(tags: List<TagEntity>)

    @Query("DELETE FROM tags WHERE repoOwner = :repoOwner AND repoName = :repoName")
    suspend fun clearTagsForRepo(repoOwner: String, repoName: String)
}
