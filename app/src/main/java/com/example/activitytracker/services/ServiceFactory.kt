package com.example.activitytracker.services

import android.content.Context
import com.example.activitytracker.services.converter.ActivityResponseToActivityCoreDataConverter
import com.example.activitytracker.services.data.DataService
import com.example.activitytracker.services.json.JsonService
import com.example.activitytracker.services.network.NetworkService

class ServiceFactory (private val context: Context) {

    private fun createNetworkService(): NetworkService {
        return NetworkService()
    }

    private fun createJsonService(): JsonService {
        return JsonService()
    }

    private fun createDataService(): DataService {
        val networkService = createNetworkService()
        val jsonService = createJsonService()
        return DataService(networkService,jsonService)
    }

    private fun createActivityConverter(): ActivityResponseToActivityCoreDataConverter {
        return ActivityResponseToActivityCoreDataConverter()
    }

    fun createSharedPreferencesService(): SharedPreferencesService{
        return SharedPreferencesService(context)
    }

    fun createActivityService(): ActivityService{
        val dataService = createDataService()
        val activityCoreDataConverter = createActivityConverter()
        return ActivityService(dataService,activityCoreDataConverter)
    }

    fun createAppServices(): ServiceContainer{
        return ServiceContainer(
            createDataService(),
            createActivityService()
        )
    }
}