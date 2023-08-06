package com.example.food.data.remote.network

import com.example.food.data.remote.entity.CategoryResponse
import com.example.food.data.remote.entity.MealResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AppServices {

    @GET("categories.php")
    suspend fun getAllCategory(): CategoryResponse

    @GET("filter.php")
    suspend fun getMealsByCategories(@Query("c") category: String?): MealResponse

}