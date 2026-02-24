package com.example.githubinnova.data.remote

import com.example.githubinnova.BuildConfig
import com.example.githubinnova.data.api.GithubApi
import com.example.githubinnova.data.mapper.toDomain
import com.example.githubinnova.data.mapper.toDomainOrNull
import com.example.githubinnova.domain.model.Repo
import com.example.githubinnova.domain.model.Tag
import com.example.githubinnova.domain.model.User
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api: GithubApi
) {
    private val token: String get() = BuildConfig.GITHUB_TOKEN

    suspend fun getUser(name: String): Result<User> = runCatching {
        api.getUser(token, name).toDomainOrNull() ?: User()
    }

    suspend fun getUserRepos(username: String): Result<List<Repo>> = runCatching {
        api.getRepos(token, username).map { it.toDomain() }
    }

    suspend fun getRepoDetails(userName: String, repoName: String): Result<Repo> = runCatching {
        api.getRepoDetails(token, userName, repoName).toDomain()
    }

    suspend fun getRepoTags(userName: String, repoName: String): Result<List<Tag>> = runCatching {
        api.getRepoTags(token, userName, repoName).map { it.toDomainOrNull() ?: Tag() }
    }
}
