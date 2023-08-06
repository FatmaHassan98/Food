package com.example.data.remote

import com.example.domain.entity.CategoryResponse
import com.example.domain.entity.MealResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AppServices {

    @GET("categories.php")
    suspend fun getAllCategory(): CategoryResponse

    @GET("filter.php")
    suspend fun getMealsByCategories(@Query("c") category: String?): MealResponse

}