package com.example.githubinnova.data.api

import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    @GET("users/{user}")
    suspend fun getUser(@Path("user") user: String): UserDto

    @GET("users/{user}/repos")
    suspend fun getRepos(@Path("user") user: String): List<RepoDto>

    @GET("repos/{user}/{repo}")
    suspend fun getRepoDetails(
        @Path("user") user: String,
        @Path("repo") repo: String
    ): RepoDto

    @GET("repos/{user}/{repo}/tags")
    suspend fun getRepoTags(
        @Path("user") user: String,
        @Path("repo") repo: String
    ): List<TagDto>
}