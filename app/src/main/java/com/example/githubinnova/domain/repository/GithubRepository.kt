package com.example.githubinnova.domain.repository

import com.example.githubinnova.domain.model.Repo
import com.example.githubinnova.domain.model.RepoDetails
import kotlinx.coroutines.flow.Flow

interface GithubRepository {

    fun observeUserRepos(username: String): Flow<List<Repo>>

    suspend fun refreshUserRepos(username: String): Result<Unit>

    fun observeRepoDetails(userName: String, repoName: String): Flow<RepoDetails?>

    suspend fun refreshRepoDetails(userName: String, repoName: String): Result<Unit>
}
