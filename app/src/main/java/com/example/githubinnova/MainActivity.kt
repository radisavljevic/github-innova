package com.example.githubinnova

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.githubinnova.feature.repo_details.RepoDetailsScreen
import com.example.githubinnova.feature.repos.ReposScreen
import com.example.githubinnova.navigation.Screen
import com.example.githubinnova.ui.theme.GithubInnovaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            GithubInnovaTheme {
                NavHost(navController = navController, startDestination = Screen.Repos.route) {
                    composable(Screen.Repos.route) {
                        ReposScreen(
                            onRepoClick = { repo ->
                                navController.navigate(
                                    Screen.RepoDetails.createRoute(
                                        username = repo.owner?.login ?: "",
                                        repoName = repo.name ?: ""
                                    )
                                )
                            }
                        )
                    }

                    composable(
                        route = Screen.RepoDetails.route,
                        arguments = listOf(
                            navArgument("username") { type = NavType.StringType },
                            navArgument("repoName") { type = NavType.StringType }
                        )
                    ) { backStackEntry ->
                        val username = backStackEntry.arguments?.getString("username") ?: ""
                        val repoName = backStackEntry.arguments?.getString("repoName") ?: ""
                        RepoDetailsScreen(userName = username, repoName = repoName)
                    }
                }
            }
        }
    }
}