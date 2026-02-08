package com.example.githubinnova.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TagDto(
    @SerialName("name") val name: String? = null,
    @SerialName("zipball_url") val zipballUrl: String? = null,
    @SerialName("tarball_url") val tarballUrl: String? = null,
    @SerialName("commit") val commit: CommitDto? = null,
    @SerialName("node_id") val nodeId: String? = null
)
