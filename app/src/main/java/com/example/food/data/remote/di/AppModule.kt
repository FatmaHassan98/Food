package com.example.food.data.remote.di

import com.example.food.data.remote.dataSource.RemoteDataSource
import com.example.food.data.remote.dataSource.RemoteDataSourceImp
import com.example.food.data.remote.network.AppServices
import com.example.food.data.repo.Repository
import com.example.food.data.repo.RepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun createRepo(dataSource: RemoteDataSource) : Repository{
        return RepositoryImp(dataSource)
    }

    @Provides
    fun getRemoteDataSource(appServices: AppServices) : RemoteDataSource{
        return RemoteDataSourceImp(appServices)
    }

    @Provides
    fun createServices(retrofit: Retrofit): AppServices {
        return retrofit.create(AppServices::class.java)
    }

    @Provides
    fun createRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

}