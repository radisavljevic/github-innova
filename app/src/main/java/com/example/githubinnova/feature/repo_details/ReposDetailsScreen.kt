package com.example.githubinnova.feature.repo_details


import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.githubinnova.core.ui.UiState
import com.example.githubinnova.domain.model.Repo
import com.example.githubinnova.domain.model.Tag

@Composable
fun RepoDetailsScreen(
    viewModel: RepoDetailsViewModel = hiltViewModel(),
    repoName: String
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(repoName) {
        viewModel.loadDetails(repoName)
    }

    when (state) {
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

        is UiState.Success<Pair<Repo, List<Tag>>> -> {
            val (repo, tags) = (state as UiState.Success<Pair<Repo, List<Tag>>>).data
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(WindowInsets.systemBars.asPaddingValues()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = repo.owner?.avatarUrl,
                            contentDescription = null,
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = repo.owner?.name ?: repo.owner?.login ?: "",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = repo.name ?: "",
                                style = MaterialTheme.typography.titleSmall
                            )
                            Text(
                                "Forks: ${repo.forksCount}  Watchers: ${repo.watchersCount}",
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    }
                }

//                items(tags, key = { it.name ?: it.sha }) { tag ->
//                    Column(
//                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
//                    ) {
//                        Text(tag.name ?: "")
//                        Text(tag.sha ?: "", fontSize = 12.sp, color = Color.Gray)
//                    }
//                }
            }
        }
    }
}