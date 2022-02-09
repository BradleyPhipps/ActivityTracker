package com.example.activitytracker.services

import android.util.Log
import com.example.activitytracker.models.ActivityCoreData
import com.example.activitytracker.models.ActivityResponse
import com.example.activitytracker.services.converter.ActivityResponseToActivityCoreDataConverter
import com.example.activitytracker.services.data.DataService
import com.example.activitytracker.services.results.DataServiceResult
import kotlinx.coroutines.ExperimentalCoroutinesApi

class ActivityService(
    private val dataService: DataService,
    private val activityCoreDataConverter: ActivityResponseToActivityCoreDataConverter
    ) {

    companion object{
        const val activityRequestUrl = "https://www.boredapi.com/api/activity/"
        const val activityKeyRequestUrl = "https://www.boredapi.com/api/activity?key="
    }

    @ExperimentalCoroutinesApi
    suspend fun getRandomSingleActivity(): ActivityCoreData{
        return when(val response = dataService.requestApiDataToObject<ActivityResponse>(activityRequestUrl)){
           is DataServiceResult.Success-> activityCoreDataConverter.convert(response.data)
           is DataServiceResult.Error-> throw KotlinNullPointerException(response.exception.localizedMessage)
           }
    }

    @ExperimentalCoroutinesApi
    suspend fun getRandomActivities(numOfActivities: Int): MutableList<ActivityCoreData> {
        val listOfActivities = mutableListOf<ActivityCoreData>()

        for (i in 0..numOfActivities) {
            when (val response = dataService.requestApiDataToObject<ActivityResponse>(activityRequestUrl)) {
                is DataServiceResult.Success -> listOfActivities.add(activityCoreDataConverter.convert(response.data))
                is DataServiceResult.Error -> throw KotlinNullPointerException(response.exception.localizedMessage)
            }
        }
        return listOfActivities
    }

    @ExperimentalCoroutinesApi
    suspend fun getSingleActivity(key: String): ActivityCoreData{
        Log.d("Logs: ","https://www.boredapi.com/api/activity?key=$key")
        return when(val response = dataService.requestApiDataToObject<ActivityResponse>(activityKeyRequestUrl+key)){
            is DataServiceResult.Success-> activityCoreDataConverter.convert(response.data)
            is DataServiceResult.Error-> throw KotlinNullPointerException(response.exception.localizedMessage)
        }
    }



    @ExperimentalCoroutinesApi
    suspend fun getSavedActivities(activityKeys: List<String>): MutableList<ActivityCoreData> {
        val listOfActivities = mutableListOf<ActivityCoreData>()

        for (element in activityKeys) {
            when (val response = dataService.requestApiDataToObject<ActivityResponse>(activityKeyRequestUrl+ element)) {
                is DataServiceResult.Success -> listOfActivities.add(activityCoreDataConverter.convert(response.data))
                is DataServiceResult.Error -> throw KotlinNullPointerException(response.exception.localizedMessage)
            }
        }
        return listOfActivities
    }
}