package com.example.food.di

import com.example.domain.repo.Repository
import com.example.domain.usecase.UseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun useCaseCategory(repository: Repository) : UseCase{
        return UseCase(repository)
    }
}