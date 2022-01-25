package com.example.activitytracker.services

import com.example.activitytracker.services.results.DataServiceResult
import com.example.activitytracker.services.results.NetworkResult
import kotlinx.coroutines.ExperimentalCoroutinesApi

class DataService(
    private val networkService: NetworkService,
    private val jsonService: JsonService
    ) {

    var loadingState: DataLoadingState = DataLoadingState.IDLE

    @ExperimentalCoroutinesApi
    internal suspend inline fun <reified T> requestApiDataToObject(urlString: String): DataServiceResult<T> {
        loadingState = DataLoadingState.LOADING
        return when(val response = networkService.makeNetworkRequest(urlString)){
            is NetworkResult.Success-> {
                loadingState = DataLoadingState.LOADED
                DataServiceResult.Success(jsonService.convertToJson(response.data))
            }
            is NetworkResult.Error-> {
                loadingState = DataLoadingState.ERROR
                DataServiceResult.Error(response.exception)
            }
        }
    }
}

enum class DataLoadingState {
    IDLE,
    LOADING,
    LOADED,
    ERROR
}