package com.example.githubinnova.feature.repos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.githubinnova.domain.model.Repo
import com.example.githubinnova.ui.theme.Spacing

@Composable
fun RepoList(
    repos: List<Repo>,
    onRepoClick: (Repo) -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = Spacing.screenHorizontal,
            top = Spacing.listTop,
            end = Spacing.screenHorizontal,
            bottom = Spacing.listBottom
        ),
        verticalArrangement = Arrangement.spacedBy(Spacing.listItemSpacingTight)
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