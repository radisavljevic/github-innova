package com.example.githubinnova.feature.repos

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.text.input.ImeAction
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.githubinnova.EmptyContentTestActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ReposScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<EmptyContentTestActivity>()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun reposScreen_displaysSearchBarAndLoadedRepoList() {
        composeRule.setContent {
            ReposScreen(onRepoClick = {})
        }

        composeRule.onNodeWithText("Search by GitHub username").assertIsDisplayed()
        composeRule.onNodeWithText("Enter a GitHub username above to see their repositories").assertIsDisplayed()

        composeRule.onNodeWithTag("search_field").performTextInput("octocat")
        composeRule.onNodeWithTag("search_field").performImeAction(ImeAction.Search)

        composeRule.waitUntil(timeoutMillis = 5_000) {
            composeRule.onAllNodesWithText("TestRepo").fetchSemanticsNodes().isNotEmpty()
        }

        composeRule.onNodeWithText("TestRepo").assertIsDisplayed()
        composeRule.onNodeWithText("This is a test repository").assertIsDisplayed()
        composeRule.onNodeWithText("Open issues: 2").assertIsDisplayed()
    }
}
