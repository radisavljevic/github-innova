package com.example.githubinnova.di

import com.example.githubinnova.core.di.AppModule
import com.example.githubinnova.data.repository.FakeAndroidGithubRepository
import com.example.githubinnova.domain.repository.GithubRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class]
)
abstract class TestRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindGithubRepository(
        fakeRepo: FakeAndroidGithubRepository
    ): GithubRepository
}
