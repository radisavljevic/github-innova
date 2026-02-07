package com.example.githubinnova.data.repository

import com.example.githubinnova.core.network.ErrorHandler
import com.example.githubinnova.data.api.GithubApi
import com.example.githubinnova.data.mapper.toDomain
import com.example.githubinnova.data.mapper.toDomainOrNull
import com.example.githubinnova.data.model.UserDto
import com.example.githubinnova.domain.model.Repo
import com.example.githubinnova.domain.model.User
import com.example.githubinnova.domain.repository.GithubRepository
import com.example.githubinnova.domain.model.Tag
import javax.inject.Inject


class GithubRepositoryImpl @Inject constructor(
    private val api: GithubApi
) : GithubRepository {

    override suspend fun getUser(): Result<User> =
        runCatching {
            api.getUser("octocat")?.toDomainOrNull() ?: User()
        }

    override suspend fun getUserRepos(): Result<List<Repo>> =
        runCatching {
            api.getRepos("octocat").map { it.toDomain() }
        }

    override suspend fun getRepoDetails(repo: String): Result<Repo> =
        runCatching {
            api.getRepoDetails("octocat", repo).toDomain()
        }

    override suspend fun getRepoTags(repo: String): Result<List<Tag>> =
        runCatching {
            api.getRepoTags("octocat", repo).map { it.toDomainOrNull() ?: Tag() }
        }
}

