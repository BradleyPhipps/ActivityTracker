package com.example.activitytracker.services

import com.example.activitytracker.models.ActivityCoreData
import com.example.activitytracker.models.ActivityResponse
import com.example.activitytracker.services.results.DataServiceResult
import kotlinx.coroutines.ExperimentalCoroutinesApi

class ActivityService(
    private val dataService: DataService,
    private val activityCoreDataConverter: ActivityResponseToActivityCoreDataConverter
    ) {

    @ExperimentalCoroutinesApi
    suspend fun getSingleActivity(): ActivityCoreData{
        return when(val response = dataService.requestApiDataToObject<ActivityResponse>("https://www.boredapi.com/api/activity/")){
           is DataServiceResult.Success-> activityCoreDataConverter.convert(response.data)
           is DataServiceResult.Error-> throw KotlinNullPointerException(response.exception.localizedMessage)
           }
    }

    @ExperimentalCoroutinesApi
    suspend fun getActivities(numOfActivities: Int): MutableList<ActivityCoreData> {

        val listOfActivities = mutableListOf<ActivityCoreData>()

        for (i in 0..numOfActivities) {
            when (val response = dataService.requestApiDataToObject<ActivityResponse>("https://www.boredapi.com/api/activity/")) {
                is DataServiceResult.Success -> listOfActivities.add(activityCoreDataConverter.convert(response.data))
                is DataServiceResult.Error -> throw KotlinNullPointerException(response.exception.localizedMessage)
            }
        }
        return listOfActivities
    }
}