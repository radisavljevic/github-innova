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
    private val errorHandler: ErrorHandler
) : ViewModel() {

    private val _state = MutableStateFlow<UiState<List<Repo>>>(UiState.Loading)
    val state = _state.asStateFlow()

    init {
        loadRepos()
    }

    private fun loadRepos() = viewModelScope.launch {
        _state.value = UiState.Loading

        repository.getUserRepos()
            .onSuccess {
                _state.value = UiState.Success(it)
            }
            .onFailure {
                _state.value = UiState.Error(errorHandler.handle(it))
            }
    }
}