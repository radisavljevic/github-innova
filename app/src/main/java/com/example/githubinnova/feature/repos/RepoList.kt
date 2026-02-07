package com.example.githubinnova.feature.repos

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.githubinnova.domain.model.Repo

@Composable
fun RepoList(
    repos: List<Repo>,
    onRepoClick: (Repo) -> Unit = {}
) {
    val bottomPadding: Dp = with(LocalDensity.current) {
        WindowInsets.navigationBars.getBottom(this).toDp()
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            top = 8.dp,
            bottom = bottomPadding + 8.dp
        )
    ) {
        items(
            items = repos,
            key = { it.name ?: "N/A" }
        ) { repo ->
            RepoItem(
                repo = repo,
                onClick = { onRepoClick(repo) }
            )
        }
    }
}