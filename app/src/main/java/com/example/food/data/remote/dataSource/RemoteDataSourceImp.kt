package com.example.food.data.remote.dataSource

import com.example.food.data.remote.entity.CategoryResponse
import com.example.food.data.remote.entity.MealResponse
import com.example.food.data.remote.network.AppServices
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(private val appServices: AppServices) : RemoteDataSource {

    override suspend fun getCategories(): CategoryResponse {
        return appServices.getAllCategory()
    }

    override suspend fun getMeals(category: String): MealResponse {
        return appServices.getMealsByCategories(category)
    }
}