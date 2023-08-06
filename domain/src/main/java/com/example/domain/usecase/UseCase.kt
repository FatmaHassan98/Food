package com.example.domain.usecase

import com.example.domain.entity.Category
import com.example.domain.entity.Meal
import com.example.domain.repo.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class UseCase(private val repository: Repository) {

    suspend fun getCategories(): Flow<List<Category>> {
        return flowOf(repository.getCategories().categories)
    }

    suspend fun getMeals(category: String): Flow<List<Meal>> {
        return flowOf(repository.getMeals(category).meals)
    }
}