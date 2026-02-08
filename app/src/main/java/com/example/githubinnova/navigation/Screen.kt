package com.example.githubinnova.navigation

sealed class Screen(val route: String) {
    object Repos : Screen("repos")
    object RepoDetails : Screen("repo_details/{username}/{repoName}") {
        fun createRoute(username: String, repoName: String) =
            "repo_details/$username/$repoName"
    }
}