package com.example.food.data.repo

import com.example.food.data.remote.entity.Category
import com.example.food.data.remote.entity.Meal
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getCategories(): Flow<List<Category>>
    suspend fun getMeals(category: String): Flow<List<Meal>>
}