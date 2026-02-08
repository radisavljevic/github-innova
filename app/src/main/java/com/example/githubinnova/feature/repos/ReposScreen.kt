package com.example.githubinnova.feature.repos

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.githubinnova.core.ui.UiState
import com.example.githubinnova.domain.model.Repo
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

@Composable
fun ReposScreen(
    viewModel: ReposViewModel = hiltViewModel(),
    onRepoClick: (Repo) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var text by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            SearchBar(
                text = text,
                onTextChange = { text = it },
                onSearchClick = { viewModel.searchRepos(text) },
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsPadding(WindowInsets.statusBars)
            )

        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                when (state) {
                    is UiState.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    is UiState.Success<List<Repo>> -> {
                        RepoList(
                            repos = (state as UiState.Success<List<Repo>>).data,
                            onRepoClick = onRepoClick,
                        )
                    }

                    is UiState.Error -> {
                        Text(
                            text = (state as UiState.Error).message,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    )
}