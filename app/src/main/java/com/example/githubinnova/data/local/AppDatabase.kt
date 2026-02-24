package com.example.githubinnova.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.githubinnova.data.local.dao.ReposDao
import com.example.githubinnova.data.local.dao.TagsDao
import com.example.githubinnova.data.local.entity.RepoEntity
import com.example.githubinnova.data.local.entity.TagEntity

@Database(
    entities = [RepoEntity::class, TagEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun reposDao(): ReposDao
    abstract fun tagsDao(): TagsDao
}
