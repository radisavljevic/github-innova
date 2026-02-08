package com.example.githubinnova

import com.example.githubinnova.data.repository.FakeAndroidGithubRepository
import com.example.githubinnova.feature.repo_details.RepoDetailsViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest

import org.junit.Test

import org.junit.Before
import org.junit.Rule
import javax.inject.Inject
import com.example.githubinnova.core.network.ErrorHandler
import com.example.githubinnova.core.ui.UiState
import kotlinx.coroutines.test.runTest
import org.junit.Assert

@HiltAndroidTest
class RepoDetailsViewModelInstrumentedTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var fakeRepo: FakeAndroidGithubRepository

    private lateinit var viewModel: RepoDetailsViewModel

    @Before
    fun setup() {
        hiltRule.inject()
        viewModel = RepoDetailsViewModel(fakeRepo, object : ErrorHandler {
            override fun handle(throwable: Throwable) = throwable.message ?: "Unknown error"
        })
    }

    @Test
    fun testLoadDetailsSuccess() = runTest {
        fakeRepo.shouldReturnError = false
        viewModel.loadDetails("octocat", "TestRepo")
        val state = viewModel.state.value
        Assert.assertTrue(state is UiState.Success)
    }
}