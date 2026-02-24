package com.example.githubinnova.data.local

import com.example.githubinnova.data.local.entity.TagEntity
import com.example.githubinnova.domain.model.Commit
import com.example.githubinnova.domain.model.Tag

fun TagEntity.toDomain(): Tag = Tag(
    name = tagName,
    commit = Commit(sha = commitSha)
)

fun Tag.toEntity(repoOwner: String, repoName: String): TagEntity = TagEntity(
    repoOwner = repoOwner,
    repoName = repoName,
    tagName = name.orEmpty(),
    commitSha = commit?.sha
)
