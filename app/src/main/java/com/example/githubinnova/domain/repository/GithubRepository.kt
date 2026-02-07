package com.example.githubinnova.domain.repository

import com.example.githubinnova.domain.model.Repo
import com.example.githubinnova.domain.model.Tag
import com.example.githubinnova.domain.model.User

interface GithubRepository {
    suspend fun getUser(): Result<User>
    suspend fun getUserRepos(): Result<List<Repo>>
    suspend fun getRepoDetails(repo: String): Result<Repo>
    suspend fun getRepoTags(repo: String): Result<List<Tag>>
}