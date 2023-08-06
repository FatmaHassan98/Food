package com.example.food.data.repo

import com.example.food.data.remote.dataSource.RemoteDataSource
import com.example.food.data.remote.entity.Category
import com.example.food.data.remote.entity.Meal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class RepositoryImp @Inject constructor(private val dataSource: RemoteDataSource) : Repository {

    override suspend fun getCategories(): Flow<List<Category>> {
        return flowOf(dataSource.getCategories().categories)
    }

    override suspend fun getMeals(category: String): Flow<List<Meal>> {
        return flowOf(dataSource.getMeals(category).meals)
    }
}