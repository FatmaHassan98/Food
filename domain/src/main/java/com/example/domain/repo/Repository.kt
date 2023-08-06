package com.example.domain.repo

import com.example.domain.entity.CategoryResponse
import com.example.domain.entity.MealResponse

interface Repository {

    suspend fun getCategories(): CategoryResponse
    suspend fun getMeals(category: String): MealResponse
}