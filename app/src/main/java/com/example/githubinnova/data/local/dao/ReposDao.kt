package com.example.githubinnova.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubinnova.data.local.entity.RepoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ReposDao {

    @Query("SELECT * FROM repos WHERE ownerLogin = :ownerLogin ORDER BY name ASC")
    fun getReposByOwner(ownerLogin: String): Flow<List<RepoEntity>>

    @Query("SELECT * FROM repos WHERE ownerLogin = :owner AND name = :repoName LIMIT 1")
    fun getRepoByOwnerAndName(owner: String, repoName: String): Flow<RepoEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepos(repos: List<RepoEntity>)

    @Query("DELETE FROM repos WHERE ownerLogin = :ownerLogin")
    suspend fun clearReposForOwner(ownerLogin: String)
}
