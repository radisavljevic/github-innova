package com.example.githubinnova.data.mapper

import com.example.githubinnova.data.model.RepoDto
import com.example.githubinnova.domain.model.Repo

fun RepoDto.toDomain(): Repo {
    return Repo(
        id = id,
        name = name,
        fullName = fullName,
        private = private,
        owner = owner?.toDomainOrNull(),
        htmlUrl = htmlUrl,
        description = description,
        fork = fork,
        stargazersCount = stargazersCount ?: 0,
        watchersCount = watchersCount ?: 0,
        language = language,
        forksCount = forksCount ?: 0,
        openIssuesCount = openIssuesCount ?: 0,
        defaultBranch = defaultBranch ?: "",
        license = license?.toDomainOrNull(),
    )
}