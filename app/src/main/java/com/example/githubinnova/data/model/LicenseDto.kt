package com.example.githubinnova.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LicenseDto(
    @SerialName("key") val key: String,
    @SerialName("name") val name: String,
    @SerialName("spdx_id") val spdxId: String,
    @SerialName("url") val url: String? = null,
    @SerialName("node_id") val nodeId: String
)
