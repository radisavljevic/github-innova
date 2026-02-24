package com.example.githubinnova.data.repository

import com.example.githubinnova.data.local.LocalDataSource
import com.example.githubinnova.data.remote.RemoteDataSource
import com.example.githubinnova.domain.model.Repo
import com.example.githubinnova.domain.model.RepoDetails
import com.example.githubinnova.domain.repository.GithubRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource
) : GithubRepository {

    override fun observeUserRepos(username: String): Flow<List<Repo>> =
        local.observeReposByOwner(username)

    override suspend fun refreshUserRepos(username: String): Result<Unit> {
        return remote.getUserRepos(username)
            .onSuccess { repos -> local.saveRepos(username, repos) }
            .map { }


    }

    override fun observeRepoDetails(userName: String, repoName: String): Flow<RepoDetails?> =
        local.observeRepoDetails(userName, repoName)

    override suspend fun refreshRepoDetails(userName: String, repoName: String): Result<Unit> {
        val repoResult = remote.getRepoDetails(userName, repoName)
        val tagsResult = remote.getRepoTags(userName, repoName)
        val repo = repoResult.getOrNull()
        val tags = tagsResult.getOrNull()
        return if (repo != null && tags != null) {
            local.saveRepoDetails(userName, repoName, repo, tags)
            Result.success(Unit)
        } else {
            Result.failure(
                repoResult.exceptionOrNull()
                    ?: tagsResult.exceptionOrNull()
                    ?: Exception("Unknown error")
            )
        }
    }
}
