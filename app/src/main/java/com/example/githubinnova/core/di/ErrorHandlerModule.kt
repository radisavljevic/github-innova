package com.example.githubinnova.core.di

import com.example.githubinnova.core.network.DefaultErrorHandler
import com.example.githubinnova.core.network.ErrorHandler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ErrorHandlerModule {

    @Binds
    @Singleton
    abstract fun bindErrorHandler(
        impl: DefaultErrorHandler
    ): ErrorHandler
}