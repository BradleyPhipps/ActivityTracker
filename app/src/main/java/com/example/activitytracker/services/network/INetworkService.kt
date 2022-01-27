package com.example.activitytracker.services.network

import com.example.activitytracker.services.results.NetworkResult

interface INetworkService {

    suspend fun makeNetworkRequest(url: String): NetworkResult<String>
}