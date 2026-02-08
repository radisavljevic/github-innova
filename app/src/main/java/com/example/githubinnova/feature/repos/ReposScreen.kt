package com.example.githubinnova.feature.repos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

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

@Composable
fun SearchBar(
    text: String,
    onTextChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        modifier = modifier.padding(
            start = 16.dp,
            top = 24.dp,
            end = 16.dp,
            bottom = 8.dp,
        ),
    ) {
        TextField(
            value = text,
            onValueChange = onTextChange,
            placeholder = { Text("Search repos by user name") },
            singleLine = true,
            modifier = Modifier
                .weight(1f),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClick()
                    keyboardController?.hide()
                }
            )
        )

        IconButton(
            onClick = onSearchClick,
            modifier = Modifier.size(56.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search"
            )
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


