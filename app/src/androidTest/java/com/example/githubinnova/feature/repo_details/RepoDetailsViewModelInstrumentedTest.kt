package com.example.githubinnova.feature.repo_details

import com.example.githubinnova.data.repository.FakeAndroidGithubRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest

import org.junit.Test
import org.junit.After
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject
import com.example.githubinnova.core.network.ErrorHandler
import com.example.githubinnova.core.ui.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class RepoDetailsViewModelInstrumentedTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var fakeRepo: FakeAndroidGithubRepository

    private lateinit var viewModel: RepoDetailsViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        hiltRule.inject()
        Dispatchers.setMain(testDispatcher)
        viewModel = RepoDetailsViewModel(fakeRepo, object : ErrorHandler {
            override fun handle(throwable: Throwable) = throwable.message ?: "Unknown error"
        })
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testLoadDetailsSuccess() = runTest {
        fakeRepo.shouldReturnError = false
        viewModel.loadDetails("octocat", "TestRepo")
        testDispatcher.scheduler.advanceUntilIdle()
        val state = viewModel.state.value
        Assert.assertTrue(state is UiState.Success)
    }
}
