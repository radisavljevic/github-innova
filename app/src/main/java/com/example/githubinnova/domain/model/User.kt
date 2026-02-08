package com.example.githubinnova.domain.model

data class User(
    val login: String? = null,
    val id: Long? = null,
    val avatarUrl: String? = null,
    val name: String? = null,
    val company: String? = null,
    val blog: String? = null,
    val location: String? = null,
    val email: String? = null,
    val bio: String? = null,
    val twitterUsername: String? = null,
    val publicRepos: Long? = null,
    val followers: Long? = null,
    val following: Long? = null
)