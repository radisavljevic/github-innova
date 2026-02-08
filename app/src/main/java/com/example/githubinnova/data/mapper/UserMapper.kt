package com.example.githubinnova.data.mapper

import com.example.githubinnova.data.model.UserDto
import com.example.githubinnova.domain.model.User

fun UserDto.toDomainOrNull(): User? {
    return User(
        login = login,
        id = id,
        avatarUrl = avatarUrl,
        name = name,
        company = company,
        blog = blog,
        location = location,
        email = email,
        bio = bio,
        twitterUsername = twitterUsername,
        publicRepos = publicRepos ?: 0,
        followers = followers ?: 0,
        following = following ?: 0
    )
}