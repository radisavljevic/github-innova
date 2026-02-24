package com.example.githubinnova.data.local

import com.example.githubinnova.data.local.entity.RepoEntity
import com.example.githubinnova.domain.model.Repo
import com.example.githubinnova.domain.model.User

fun RepoEntity.toDomain(): Repo = Repo(
    id = id,
    name = name,
    fullName = fullName,
    description = description,
    forksCount = forksCount,
    watchersCount = watchersCount,
    openIssuesCount = openIssuesCount,
    defaultBranch = defaultBranch,
    owner = User(
        login = ownerLogin,
        avatarUrl = ownerAvatarUrl,
        name = ownerName
    ),
    stargazersCount = 0,
    language = null,
    license = null
)

fun Repo.toEntity(ownerLogin: String): RepoEntity = RepoEntity(
    id = id ?: 0L,
    name = name,
    fullName = fullName,
    description = description,
    forksCount = forksCount,
    watchersCount = watchersCount,
    openIssuesCount = openIssuesCount,
    ownerLogin = ownerLogin,
    ownerAvatarUrl = owner?.avatarUrl,
    ownerName = owner?.name ?: owner?.login,
    defaultBranch = defaultBranch
)
