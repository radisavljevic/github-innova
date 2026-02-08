package com.example.githubinnova.domain.repository

import com.example.githubinnova.domain.model.Repo
import com.example.githubinnova.domain.model.Tag
import com.example.githubinnova.domain.model.User

interface GithubRepository {
    suspend fun getUser(name: String): Result<User>
    suspend fun getUserRepos(name: String): Result<List<Repo>>
    suspend fun getRepoDetails(userName: String, repo: String): Result<Repo>
    suspend fun getRepoTags(userName: String, repo: String): Result<List<Tag>>
}