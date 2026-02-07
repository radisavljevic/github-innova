package com.example.githubinnova.data.mapper

import com.example.githubinnova.data.model.CommitDto
import com.example.githubinnova.domain.model.Commit

fun CommitDto.toDomain(): Commit {
    return Commit(
        sha = sha,
        url = url
    )
}