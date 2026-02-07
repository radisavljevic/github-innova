package com.example.githubinnova.domain.model

data class Tag(
    val name: String,
    val zipballUrl: String,
    val tarballUrl: String,
    val commit: Commit,
    val nodeId: String
)