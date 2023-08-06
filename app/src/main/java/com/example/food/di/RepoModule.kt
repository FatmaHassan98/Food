package com.example.food.di

import com.example.data.remote.AppServices
import com.example.data.repo.RepositoryImp
import com.example.domain.repo.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun createRepo(appServices: AppServices) : Repository{
        return RepositoryImp(appServices)
    }
}