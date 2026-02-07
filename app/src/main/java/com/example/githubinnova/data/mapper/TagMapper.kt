package com.example.githubinnova.data.mapper

import com.example.githubinnova.data.model.TagDto
import com.example.githubinnova.domain.model.Tag

fun TagDto.toDomain(): Tag {
    return Tag(
        name = name,
        zipballUrl = zipballUrl,
        tarballUrl = tarballUrl,
        commit = commit.toDomain(),
        nodeId = nodeId
    )
}