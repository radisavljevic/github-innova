package com.example.githubinnova.domain.model

data class User(
    val login: String,
    val id: Long,
    val avatarUrl: String,
    val name: String?,
    val company: String?,
    val blog: String?,
    val location: String?,
    val email: String?,
    val bio: String?,
    val twitterUsername: String?,
    val publicRepos: Long,
    val followers: Long,
    val following: Long
)