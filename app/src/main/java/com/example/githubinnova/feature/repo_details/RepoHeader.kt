package com.example.githubinnova.feature.repo_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.githubinnova.domain.model.Repo

@Composable
fun RepoHeader(repo: Repo) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = repo.owner?.avatarUrl,
            contentDescription = "Owner Avatar",
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = repo.owner?.name ?: repo.owner?.login.orEmpty(),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = repo.name.orEmpty(),
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                "Forks: ${repo.forksCount}  Watchers: ${repo.watchersCount}",
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}