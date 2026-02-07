package com.example.githubinnova.domain.repository

import com.example.githubinnova.data.model.RepoDto
import com.example.githubinnova.data.model.TagDto
import com.example.githubinnova.domain.model.Repo
import com.example.githubinnova.domain.model.Tag
import com.example.githubinnova.domain.model.User

interface GithubRepository {
    suspend fun getUser(): User
    suspend fun getUserRepos(): List<Repo>
    suspend fun getRepoDetails(repo: String): Repo
    suspend fun getRepoTags(repo: String): List<Tag>
}