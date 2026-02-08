package com.example.githubinnova.feature.repos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubinnova.core.network.ErrorHandler
import com.example.githubinnova.core.ui.UiState
import com.example.githubinnova.domain.model.Repo
import com.example.githubinnova.domain.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReposViewModel @Inject constructor(
    private val repository: GithubRepository,
    private val errorHandler: ErrorHandler,
) : ViewModel() {

    private val _state = MutableStateFlow<UiState<List<Repo>>>(UiState.Idle)
    val state = _state.asStateFlow()

    fun searchRepos(username: String) {
        if (username.isBlank()) {
            _state.value = UiState.Idle
            return
        }
        viewModelScope.launch {
            _state.value = UiState.Loading

            val result = repository.getUserRepos(username)

            _state.value = result.fold(
                onSuccess = { UiState.Success(it) },
                onFailure = { UiState.Error(errorHandler.handle(it)) }
            )
        }
    }
}