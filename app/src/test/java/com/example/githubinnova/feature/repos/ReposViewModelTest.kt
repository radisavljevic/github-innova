package com.example.githubinnova.feature.repos

import com.example.githubinnova.core.network.ErrorHandler
import com.example.githubinnova.core.ui.UiState
import com.example.githubinnova.data.repository.FakeGithubRepository
import com.example.githubinnova.domain.model.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ReposViewModelTest {

    private lateinit var fakeRepo: FakeGithubRepository
    private lateinit var viewModel: ReposViewModel

    private val testDispatcher = StandardTestDispatcher()
    private val errorHandler = object : ErrorHandler {
        override fun handle(throwable: Throwable) = throwable.message ?: "Unknown error"
    }

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        fakeRepo = FakeGithubRepository()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun initialState_isIdle() = runTest {
        viewModel = ReposViewModel(repository = fakeRepo, errorHandler = errorHandler)
        Assert.assertTrue(viewModel.state.value is UiState.Idle)
    }

    @Test
    fun searchRepos_withBlankUsername_setsStateToIdle() = runTest {
        viewModel = ReposViewModel(repository = fakeRepo, errorHandler = errorHandler)
        viewModel.searchRepos("")
        Assert.assertTrue(viewModel.state.value is UiState.Idle)
    }

    @Test
    fun searchReposSuccess_updatesStateWithRepoList() = runTest {
        fakeRepo.shouldReturnError = false
        viewModel = ReposViewModel(repository = fakeRepo, errorHandler = errorHandler)
        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.searchRepos("octocat")
        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.state.value
        Assert.assertTrue(state is UiState.Success)
        val data = (state as UiState.Success<List<Repo>>).data
        Assert.assertEquals(1, data.size)
        Assert.assertEquals("TestRepo", data.first().name)
    }

    @Test
    fun searchReposFailure_whenApiReturnsError_updatesStateWithError() = runTest {
        fakeRepo.shouldReturnError = true
        viewModel = ReposViewModel(repository = fakeRepo, errorHandler = errorHandler)
        viewModel.searchRepos("octocat")
        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.state.value
        Assert.assertTrue(state is UiState.Error)
        Assert.assertEquals("Failed", (state as UiState.Error).message)
    }

    @Test
    fun searchReposFailure_afterSuccess_updatesStateWithError() = runTest {
        fakeRepo.shouldReturnError = false
        viewModel = ReposViewModel(repository = fakeRepo, errorHandler = errorHandler)
        viewModel.searchRepos("octocat")
        testDispatcher.scheduler.advanceUntilIdle()

        fakeRepo.shouldReturnError = true
        viewModel.searchRepos("other")
        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.state.value
        Assert.assertTrue(state is UiState.Error)
        Assert.assertEquals("Failed", (state as UiState.Error).message)
    }
}
