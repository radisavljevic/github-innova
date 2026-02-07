package com.example.githubinnova.data.mapper

import com.example.githubinnova.data.model.RepoDto
import com.example.githubinnova.domain.model.Repo

fun RepoDto.toDomain(): Repo {
    return Repo(
        id = id,
        name = name,
        fullName = fullName,
        private = private,
        owner = owner.toDomain(),   // map UserDto -> User
        htmlUrl = htmlUrl,
        description = description,
        fork = fork,
        stargazersCount = stargazersCount,
        watchersCount = watchersCount,
        language = language,
        forksCount = forksCount,
        openIssuesCount = openIssuesCount,
        defaultBranch = defaultBranch
    )
}