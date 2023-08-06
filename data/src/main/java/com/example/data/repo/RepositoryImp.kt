package com.example.data.repo

import com.example.data.remote.AppServices
import com.example.domain.entity.CategoryResponse
import com.example.domain.entity.MealResponse
import com.example.domain.repo.Repository


class RepositoryImp(private val appServices: AppServices) : Repository {

    override suspend fun getCategories(): CategoryResponse {
        return appServices.getAllCategory()
    }

    override suspend fun getMeals(category: String): MealResponse {
        return appServices.getMealsByCategories(category)
    }
}