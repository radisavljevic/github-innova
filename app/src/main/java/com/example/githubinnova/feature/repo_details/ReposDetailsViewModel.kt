package com.example.githubinnova.feature.repo_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubinnova.core.network.ErrorHandler
import com.example.githubinnova.core.ui.UiState
import com.example.githubinnova.domain.model.RepoDetails
import com.example.githubinnova.domain.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class RepoDetailsViewModel @Inject constructor(
    private val repository: GithubRepository,
    private val errorHandler: ErrorHandler
) : ViewModel() {

    private val _state = MutableStateFlow<UiState<RepoDetails>>(UiState.Loading)
    val state = _state.asStateFlow()

    private val currentParams = MutableStateFlow<Pair<String, String>?>(null)

    init {
        viewModelScope.launch {
            currentParams
                .filterNotNull()
                .flatMapLatest { (userName, repoName) ->
                    repository.observeRepoDetails(userName, repoName)
                }
                .catch { e -> _state.value = UiState.Error(errorHandler.handle(e)) }
                .collect { details ->
                    _state.value = if (details != null) {
                        UiState.Success(details)
                    } else {
                        UiState.Loading
                    }
                }
        }
    }

    fun loadDetails(userName: String, repoName: String) = viewModelScope.launch {
        currentParams.value = userName to repoName
        _state.value = UiState.Loading
        val result = repository.refreshRepoDetails(userName, repoName)
        if (result.isFailure) {
            _state.value = UiState.Error(
                errorHandler.handle(result.exceptionOrNull() ?: Exception("Unknown error"))
            )
        }
    }
}
