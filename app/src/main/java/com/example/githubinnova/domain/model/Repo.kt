package com.example.githubinnova.domain.model

data class Repo(
    val id: Long? = null,
    val name: String? = null,
    val fullName: String? = null,
    val private: Boolean? = null,
    val owner: User? = null,
    val htmlUrl: String? = null,
    val description: String?,
    val fork: Boolean? = null,
    val stargazersCount: Int,
    val watchersCount: Int,
    val language: String?,
    val forksCount: Int,
    val openIssuesCount: Int,
    val defaultBranch: String,
    val license: License? = null,
)
