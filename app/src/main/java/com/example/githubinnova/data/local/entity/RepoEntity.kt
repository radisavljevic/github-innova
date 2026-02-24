package com.example.githubinnova.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "repos",
    indices = [Index(value = ["ownerLogin"])]
)
data class RepoEntity(
    @PrimaryKey
    val id: Long,
    val name: String?,
    val fullName: String?,
    val description: String?,
    val forksCount: Int,
    val watchersCount: Int,
    val openIssuesCount: Int,
    val ownerLogin: String,
    val ownerAvatarUrl: String?,
    val ownerName: String?,
    val defaultBranch: String = "main"
)
