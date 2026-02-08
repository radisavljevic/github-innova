package com.example.githubinnova.feature.repo_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.githubinnova.domain.model.Tag

@Composable
fun TagItem(tag: Tag) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        Text(
            text = tag.name.orEmpty(),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = tag.commit?.sha.orEmpty(),
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}