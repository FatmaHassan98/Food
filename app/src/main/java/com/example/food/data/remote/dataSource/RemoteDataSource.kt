package com.example.food.data.remote.dataSource

import com.example.food.data.remote.entity.CategoryResponse
import com.example.food.data.remote.entity.MealResponse

interface RemoteDataSource {

    suspend fun getCategories(): CategoryResponse
    suspend fun getMeals(category: String): MealResponse

}