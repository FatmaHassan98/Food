package com.example.food.utils

sealed class RemoteStatus<out T> {
    class Success<T>(val data: T) : RemoteStatus<T>()
    class Failure(val msg: Throwable) : RemoteStatus<Nothing>()
    object Loading : RemoteStatus<Nothing>()
}