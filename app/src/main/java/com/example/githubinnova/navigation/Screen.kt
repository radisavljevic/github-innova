package com.example.githubinnova.navigation

sealed class Screen(val route: String) {
    object Repos : Screen("repos")
    object RepoDetails : Screen("repo_details/{repoName}") {
        fun createRoute(repoName: String) = "repo_details/$repoName"
    }
}