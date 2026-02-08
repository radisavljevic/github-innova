package com.example.githubinnova.data.mapper

import com.example.githubinnova.data.model.CommitDto
import com.example.githubinnova.domain.model.Commit

fun CommitDto.toDomainOrNull(): Commit? = Commit(
    sha = sha,
    url = url
)