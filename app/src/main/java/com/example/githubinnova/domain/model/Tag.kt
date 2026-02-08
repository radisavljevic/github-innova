package com.example.githubinnova.domain.model

data class Tag(
    val name: String? = null,
    val zipballUrl: String? = null,
    val tarballUrl: String? = null,
    val commit: Commit? = null,
    val nodeId: String? = null
)