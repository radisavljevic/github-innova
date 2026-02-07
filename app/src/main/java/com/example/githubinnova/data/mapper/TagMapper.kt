package com.example.githubinnova.data.mapper

import com.example.githubinnova.data.model.TagDto
import com.example.githubinnova.domain.model.Tag

fun TagDto.toDomainOrNull(): Tag? = Tag(
    name = name,
    zipballUrl = zipballUrl,
    tarballUrl = tarballUrl,
    nodeId = nodeId,
    commit = commit?.toDomainOrNull()
)