package com.example.githubinnova.data.repository

import com.example.githubinnova.data.api.GithubApi
import com.example.githubinnova.data.mapper.toDomain
import com.example.githubinnova.data.mapper.toDomainOrNull
import com.example.githubinnova.domain.model.Repo
import com.example.githubinnova.domain.model.User
import com.example.githubinnova.domain.repository.GithubRepository
import com.example.githubinnova.domain.model.Tag
import javax.inject.Inject
import com.example.githubinnova.BuildConfig


class GithubRepositoryImpl @Inject constructor(
    private val api: GithubApi
) : GithubRepository {

    private val token = BuildConfig.GITHUB_TOKEN

    override suspend fun getUser(name: String): Result<User> =
        runCatching {
            api.getUser(token, name).toDomainOrNull() ?: User()
        }

    override suspend fun getUserRepos(name: String): Result<List<Repo>> =
        runCatching {
            api.getRepos(token, name).map { it.toDomain() }
        }

    override suspend fun getRepoDetails(userName: String, repo: String): Result<Repo> =
        runCatching {
            api.getRepoDetails(token, userName, repo).toDomain()
        }

    override suspend fun getRepoTags(userName: String, repo: String): Result<List<Tag>> =
        runCatching {
            api.getRepoTags(token, userName, repo).map { it.toDomainOrNull() ?: Tag() }
        }
}

