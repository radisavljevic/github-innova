package com.example.githubinnova.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommitDto(
    @SerialName("sha") val sha: String,
    @SerialName("url") val url: String
)