package com.example.activitytracker.services

import android.content.Context
import androidx.navigation.NavController
import com.example.activitytracker.SavedActivityRepository
import com.example.activitytracker.services.activity.ActivityService
import com.example.activitytracker.services.converter.ActivityResponseToActivityCoreDataConverter
import com.example.activitytracker.services.data.DataService
import com.example.activitytracker.services.data.SharedPreferencesService
import com.example.activitytracker.services.json.JsonService
import com.example.activitytracker.services.navigation.NavigationService
import com.example.activitytracker.services.network.NetworkService

class ServiceFactory (private val context: Context, private val navController: NavController) {

    private fun createNetworkService(): NetworkService {
        return NetworkService()
    }

    fun createJsonService(): JsonService {
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

    fun createSharedPreferencesService(): SharedPreferencesService {
        return SharedPreferencesService(context)
    }

    fun createSavedActivityRepository(): SavedActivityRepository{
        return SavedActivityRepository(createSharedPreferencesService())
    }

    fun createActivityService(): ActivityService {
        val dataService = createDataService()
        val activityCoreDataConverter = createActivityConverter()
        return ActivityService(dataService,activityCoreDataConverter)
    }

    fun createNavigationService(): NavigationService {
        val jsonService = createJsonService()
        return NavigationService(navController, jsonService)
    }
}