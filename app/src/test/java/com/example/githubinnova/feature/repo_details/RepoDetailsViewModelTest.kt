package com.example.githubinnova.feature.repo_details

import kotlinx.coroutines.test.runTest
import com.example.githubinnova.core.network.ErrorHandler
import com.example.githubinnova.core.ui.UiState
import com.example.githubinnova.data.repository.FakeGithubRepository
import com.example.githubinnova.domain.model.RepoDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RepoDetailsViewModelTest {

    private lateinit var fakeRepo: FakeGithubRepository
    private lateinit var viewModel: RepoDetailsViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        fakeRepo = FakeGithubRepository()
        viewModel = RepoDetailsViewModel(
            repository = fakeRepo,
            errorHandler = object : ErrorHandler {
                override fun handle(throwable: Throwable) = throwable.message ?: "Unknown error"
            }
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun loadDetailsSuccessUpdatesStateWithRepoDetails() = runTest {
        fakeRepo.shouldReturnError = false

        viewModel.loadDetails("octocat", "TestRepo")

        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.state.value
        assert(state is UiState.Success)
        val data = (state as UiState.Success<RepoDetails>).data
        assert(data.repo.name == "TestRepo")
    }


    @Test
    fun loadDetailsFailureUpdatesStateWithError() = runTest {
        fakeRepo.shouldReturnError = true

        viewModel.loadDetails("octocat", "TestRepo")
        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.state.value
        Assert.assertTrue(state is UiState.Error)
        Assert.assertEquals("Failed", (state as UiState.Error).message)
    }
}