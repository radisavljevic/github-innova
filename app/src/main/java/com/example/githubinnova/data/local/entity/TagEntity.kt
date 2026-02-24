package com.example.githubinnova.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "tags",
    primaryKeys = ["repoOwner", "repoName", "tagName"],
    indices = [Index(value = ["repoOwner", "repoName"])]
)
data class TagEntity(
    val repoOwner: String,
    val repoName: String,
    val tagName: String,
    val commitSha: String?
)
