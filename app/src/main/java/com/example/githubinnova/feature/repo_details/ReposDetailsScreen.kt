package com.example.githubinnova.feature.repo_details

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.githubinnova.core.ui.UiState
import com.example.githubinnova.ui.theme.Spacing
import com.example.githubinnova.domain.model.RepoDetails

@Composable
fun RepoDetailsScreen(
    viewModel: RepoDetailsViewModel = hiltViewModel(),
    userName: String,
    repoName: String
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(repoName) {
        viewModel.loadDetails(userName, repoName)
    }

    when (state) {
        is UiState.Idle,
        is UiState.Loading -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }

        is UiState.Error -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text((state as UiState.Error).message)
        }

        is UiState.Success<RepoDetails> -> {
            val repoDetails = (state as UiState.Success<RepoDetails>).data
            val repo = repoDetails.repo
            val tags = repoDetails.tags

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(WindowInsets.systemBars.asPaddingValues()),
                contentPadding = PaddingValues(
                    horizontal = Spacing.screenHorizontal,
                    vertical = Spacing.screenVertical
                ),
                verticalArrangement = Arrangement.spacedBy(Spacing.listItemSpacing)
            ) {
                item { RepoHeader(repo) }
                itemsIndexed(tags, key = { index, _ -> index }) { _, tag ->
                    TagItem(tag)
                }
            }
        }
    }
}


