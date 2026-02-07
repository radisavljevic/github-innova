package com.example.githubinnova.domain.model

data class Repo(
    val id: Long,
    val name: String,
    val fullName: String,
    val private: Boolean,
    val owner: User,
    val htmlUrl: String,
    val description: String?,
    val fork: Boolean,
    val stargazersCount: Int,
    val watchersCount: Int,
    val language: String?,
    val forksCount: Int,
    val openIssuesCount: Int,
    val defaultBranch: String
)
