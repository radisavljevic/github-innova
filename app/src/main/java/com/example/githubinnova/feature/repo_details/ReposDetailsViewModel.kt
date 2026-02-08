package com.example.githubinnova.feature.repo_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubinnova.core.network.ErrorHandler
import com.example.githubinnova.core.ui.UiState
import com.example.githubinnova.domain.model.Repo
import com.example.githubinnova.domain.model.Tag
import com.example.githubinnova.domain.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoDetailsViewModel @Inject constructor(
    private val repository: GithubRepository,
    private val errorHandler: ErrorHandler
) : ViewModel() {

    private val _state = MutableStateFlow<UiState<Pair<Repo, List<Tag>>>>(UiState.Loading)
    val state = _state.asStateFlow()

    fun loadDetails(userName: String, repoName: String) = viewModelScope.launch {
        _state.value = UiState.Loading

        val repoResult = repository.getRepoDetails(userName, repoName)
        val tagsResult = repository.getRepoTags(userName, repoName)

        val repo = repoResult.getOrNull()
        val tags = tagsResult.getOrNull()

        if (repo != null && tags != null) {
            _state.value = UiState.Success(repo to tags)
        } else {
            val exception = repoResult.exceptionOrNull() ?: tagsResult.exceptionOrNull()
            _state.value =
                UiState.Error(errorHandler.handle(exception ?: Exception("Unknown error")))
        }
    }
}