package com.example.githubinnova.core.di

import com.example.githubinnova.domain.repository.GithubRepository
import com.example.githubinnova.data.repository.GithubRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindGithubRepository(
        impl: GithubRepositoryImpl
    ): GithubRepository
}