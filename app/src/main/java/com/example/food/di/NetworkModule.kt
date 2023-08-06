package com.example.food.di

import com.example.data.remote.AppServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder().connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS).build()
    }

    @Provides
    @Singleton
    fun createRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun createServices(retrofit: Retrofit): AppServices {
        return retrofit.create(AppServices::class.java)
    }

}