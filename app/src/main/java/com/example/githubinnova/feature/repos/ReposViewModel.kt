package com.example.githubinnova.feature.repos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubinnova.core.network.ErrorHandler
import com.example.githubinnova.core.ui.UiState
import com.example.githubinnova.domain.model.Repo
import com.example.githubinnova.domain.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class ReposViewModel @Inject constructor(
    private val repository: GithubRepository,
    private val errorHandler: ErrorHandler,
) : ViewModel() {

    private val _state = MutableStateFlow<UiState<List<Repo>>>(UiState.Idle)
    val state = _state.asStateFlow()

    private val currentUsername = MutableStateFlow<String?>(null)

    init {
        viewModelScope.launch {
            currentUsername
                .filter { !it.isNullOrBlank() }
                .flatMapLatest { repository.observeUserRepos(it.toString()) }
                .catch { e -> _state.value = UiState.Error(errorHandler.handle(e)) }
                .collect { _state.value = UiState.Success(it) }

        }
    }

    fun searchRepos(username: String) {
        if (username.isBlank()) {
            currentUsername.value = null
            _state.value = UiState.Idle
            return
        }
        currentUsername.value = username
        _state.value = UiState.Loading
        viewModelScope.launch {
            val result = repository.refreshUserRepos(username)
            if (result.isFailure) {
                _state.value = UiState.Error(
                    errorHandler.handle(result.exceptionOrNull() ?: Exception("Unknown error"))
                )
            }
        }
    }
}
