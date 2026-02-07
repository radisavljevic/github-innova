package com.example.githubinnova.data.repository

import com.example.githubinnova.core.network.ErrorHandler
import com.example.githubinnova.data.api.GithubApi
import com.example.githubinnova.data.mapper.toDomain
import com.example.githubinnova.domain.model.Repo
import com.example.githubinnova.domain.model.User
import com.example.githubinnova.domain.repository.GithubRepository
import com.example.githubinnova.domain.model.Tag


class GithubRepositoryImpl(
    private val api: GithubApi
) : GithubRepository {

    override suspend fun getUser(): User {
        val userDto = api.getUser("octocat")
        return userDto.toDomain()
    }

    override suspend fun getUserRepos(): List<Repo> {
        val reposDto = api.getRepos("octocat")
        return reposDto.map { it.toDomain() }
    }

    override suspend fun getRepoDetails(repo: String): Repo {
        val repoDto = api.getRepoDetails("octocat", repo)
        return repoDto.toDomain()
    }

    override suspend fun getRepoTags(repo: String): List<Tag> {
        val tagsDto = api.getRepoTags(user = "octocat", repo = repo)
        return tagsDto.map { it.toDomain() }
    }
}