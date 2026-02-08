package com.example.githubinnova.data.repository

import com.example.githubinnova.domain.model.Commit
import com.example.githubinnova.domain.model.Repo
import com.example.githubinnova.domain.model.Tag
import com.example.githubinnova.domain.model.License
import com.example.githubinnova.domain.model.User
import com.example.githubinnova.domain.repository.GithubRepository
import javax.inject.Inject

class FakeAndroidGithubRepository @Inject constructor() : GithubRepository {

    var shouldReturnError = false

    private val sampleUser = User(
        login = "octocat",
        name = "The Octocat",
        avatarUrl = "https://avatar.url"
    )

    private val sampleLicense = License(
        key = "mit",
        name = "MIT License",
        spdxId = "MIT",
        url = "https://opensource.org/licenses/MIT"
    )

    private val sampleRepo = Repo(
        id = 123456,
        name = "TestRepo",
        fullName = "octocat/TestRepo",
        private = false,
        owner = sampleUser,
        htmlUrl = "https://github.com/octocat/TestRepo",
        description = "This is a test repository",
        fork = false,
        stargazersCount = 42,
        watchersCount = 5,
        language = "Kotlin",
        forksCount = 10,
        openIssuesCount = 2,
        defaultBranch = "main",
        license = sampleLicense
    )

    private val sampleTags = listOf(
        Tag(name = "v1.0", commit = Commit("sha123")),
        Tag(name = "v1.1", commit = Commit("sha124"))
    )

    override suspend fun getUser(name: String): Result<User> =
        if (shouldReturnError) Result.failure(Exception("Failed")) else Result.success(sampleUser)

    override suspend fun getUserRepos(name: String): Result<List<Repo>> =
        if (shouldReturnError) Result.failure(Exception("Failed")) else Result.success(
            listOf(
                sampleRepo
            )
        )

    override suspend fun getRepoDetails(userName: String, repo: String): Result<Repo> =
        if (shouldReturnError) Result.failure(Exception("Failed")) else Result.success(sampleRepo)

    override suspend fun getRepoTags(userName: String, repo: String): Result<List<Tag>> =
        if (shouldReturnError) Result.failure(Exception("Failed")) else Result.success(sampleTags)
}