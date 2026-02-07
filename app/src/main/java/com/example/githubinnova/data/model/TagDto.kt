package com.example.githubinnova.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TagDto(
    @SerialName("name") val name: String,
    @SerialName("zipball_url") val zipballUrl: String,
    @SerialName("tarball_url") val tarballUrl: String,
    @SerialName("commit") val commit: CommitDto,
    @SerialName("node_id") val nodeId: String
)
