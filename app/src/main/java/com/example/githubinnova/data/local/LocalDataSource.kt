package com.example.githubinnova.data.local

import com.example.githubinnova.data.local.dao.ReposDao
import com.example.githubinnova.data.local.dao.TagsDao
import com.example.githubinnova.domain.model.Repo
import com.example.githubinnova.domain.model.RepoDetails
import com.example.githubinnova.domain.model.Tag
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val reposDao: ReposDao,
    private val tagsDao: TagsDao
) {

    fun observeReposByOwner(ownerLogin: String): Flow<List<Repo>> =
        reposDao.getReposByOwner(ownerLogin).map { list ->
            list.map { it.toDomain() }
        }

    suspend fun saveRepos(ownerLogin: String, repos: List<Repo>) {
        reposDao.clearReposForOwner(ownerLogin)
        if (repos.isNotEmpty()) {
            reposDao.insertRepos(repos.map { it.toEntity(ownerLogin) })
        }
    }

    fun observeRepoDetails(repoOwner: String, repoName: String): Flow<RepoDetails?> =
        combine(
            reposDao.getRepoByOwnerAndName(repoOwner, repoName),
            tagsDao.getTagsByRepo(repoOwner, repoName)
        ) { repoEntity, tagEntities ->
            repoEntity?.let { repo ->
                RepoDetails(
                    repo = repo.toDomain(),
                    tags = tagEntities.map { it.toDomain() }
                )
            }
        }

    suspend fun saveRepoDetails(repoOwner: String, repoName: String, repo: Repo, tags: List<Tag>) {
        reposDao.insertRepos(listOf(repo.toEntity(repoOwner)))
        tagsDao.clearTagsForRepo(repoOwner, repoName)
        if (tags.isNotEmpty()) {
            tagsDao.insertTags(tags.map { it.toEntity(repoOwner, repoName) })
        }
    }
}
