package com.example.activitytracker.services.results

sealed class DataServiceResult<out R> {
    data class Success<out T>(val data: T) : DataServiceResult<T>()
    data class Error(val exception: Exception) : DataServiceResult<Nothing>()
}
