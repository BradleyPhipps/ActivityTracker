package com.example.activitytracker.services.data

import com.example.activitytracker.services.json.JsonService
import com.example.activitytracker.services.network.INetworkService
import com.example.activitytracker.services.results.DataServiceResult
import com.example.activitytracker.services.results.NetworkResult
import kotlinx.coroutines.ExperimentalCoroutinesApi

class DataService(
    private val networkService: INetworkService,
    private val jsonService: JsonService
    ) {

    @ExperimentalCoroutinesApi
    internal suspend inline fun <reified T> requestApiDataToObject(urlString: String): DataServiceResult<T> {
        return when(val response = networkService.makeNetworkRequest(urlString)){
            is NetworkResult.Success-> {
                DataServiceResult.Success(jsonService.convertToObject(response.data))
            }
            is NetworkResult.Error-> {
                DataServiceResult.Error(response.exception)
            }
        }
    }
}
