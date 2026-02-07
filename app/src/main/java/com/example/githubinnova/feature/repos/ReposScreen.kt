package com.example.githubinnova.feature.repos

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.githubinnova.core.ui.UiState
import com.example.githubinnova.domain.model.Repo
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment

@Composable
fun ReposScreen(
    viewModel: ReposViewModel = hiltViewModel(),
    onRepoClick: (Repo) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.statusBars.asPaddingValues())
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
                    onRepoClick = onRepoClick
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


//@Preview(showBackground = true)
//@Composable
//fun RepoItemPreview() {
//    GithubInnovaTheme {
//        RepoItem(
//            repo = Repo(
//                name = "github-innova",
//                description = "Sample repository for interview task",
//                openIssuesCount = 12,
//            ),
//            onClick = {}
//        )
//    }
//}


