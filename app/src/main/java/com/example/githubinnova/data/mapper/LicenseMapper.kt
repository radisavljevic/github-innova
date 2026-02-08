package com.example.githubinnova.data.mapper

import com.example.githubinnova.data.model.LicenseDto
import com.example.githubinnova.domain.model.License

fun LicenseDto.toDomainOrNull(): License? = License(
    key = key ?: "",
    name = name ?: "",
    spdxId = spdxId ?: "",
    url = url ?: "",
    nodeId = nodeId ?: ""
)