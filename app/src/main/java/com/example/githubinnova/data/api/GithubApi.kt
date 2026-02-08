package com.example.githubinnova.data.api

import com.example.githubinnova.data.model.RepoDto
import com.example.githubinnova.data.model.TagDto
import com.example.githubinnova.data.model.UserDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface GithubApi {

    @GET("users/{user}")
    suspend fun getUser(
        @Header("Authorization") auth: String,
        @Path("user") name: String
    ): UserDto

    @GET("users/{user}/repos")
    suspend fun getRepos(
        @Header("Authorization") auth: String,
        @Path("user") user: String
    ): List<RepoDto>

    @GET("repos/{user}/{repo}")
    suspend fun getRepoDetails(
        @Header("Authorization") auth: String,
        @Path("user") user: String,
        @Path("repo") repo: String
    ): RepoDto

    @GET("repos/{user}/{repo}/tags")
    suspend fun getRepoTags(
        @Header("Authorization") auth: String,
        @Path("user") user: String,
        @Path("repo") repo: String
    ): List<TagDto>
}