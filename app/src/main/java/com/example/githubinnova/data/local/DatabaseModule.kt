package com.example.githubinnova.data.local

import android.content.Context
import androidx.room.Room
import com.example.githubinnova.data.local.dao.ReposDao
import com.example.githubinnova.data.local.dao.TagsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "githubinnova.db"
        ).build()

    @Provides
    @Singleton
    fun provideReposDao(db: AppDatabase): ReposDao = db.reposDao()

    @Provides
    @Singleton
    fun provideTagsDao(db: AppDatabase): TagsDao = db.tagsDao()
}
