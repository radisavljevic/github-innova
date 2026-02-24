package com.example.githubinnova.data.repository

import com.example.githubinnova.domain.model.Commit
import com.example.githubinnova.domain.model.Repo
import com.example.githubinnova.domain.model.RepoDetails
import com.example.githubinnova.domain.model.Tag
import com.example.githubinnova.domain.model.License
import com.example.githubinnova.domain.model.User
import com.example.githubinnova.domain.repository.GithubRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
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

    private val reposByUser = MutableStateFlow<Map<String, List<Repo>>>(emptyMap())
    private val detailsByKey = MutableStateFlow<Map<Pair<String, String>, RepoDetails>>(emptyMap())

    override fun observeUserRepos(username: String): Flow<List<Repo>> =
        reposByUser.map { it[username].orEmpty() }

    override suspend fun refreshUserRepos(username: String): Result<Unit> =
        if (shouldReturnError) Result.failure(Exception("Failed"))
        else {
            reposByUser.update { it + (username to listOf(sampleRepo)) }
            Result.success(Unit)
        }

    override fun observeRepoDetails(userName: String, repoName: String): Flow<RepoDetails?> =
        detailsByKey.map { it[userName to repoName] }

    override suspend fun refreshRepoDetails(userName: String, repoName: String): Result<Unit> =
        if (shouldReturnError) Result.failure(Exception("Failed"))
        else {
            detailsByKey.update { it + ((userName to repoName) to RepoDetails(sampleRepo, sampleTags)) }
            Result.success(Unit)
        }
}
