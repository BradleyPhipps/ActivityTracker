package com.example.activitytracker.services

import com.example.activitytracker.models.ActivityCoreData
import com.example.activitytracker.models.ActivityResponse
import com.example.activitytracker.services.results.DataServiceResult

class ActivityService(
    private val dataService: DataService,
    private val activityCoreDataConverter: ActivityResponseToActivityCoreDataConverter
) {


    suspend fun getSingleActivity(): ActivityCoreData{
        return when(val response = dataService.requestApiDataToObject<ActivityResponse>("https://www.boredapi.com/api/activity/")){
           is DataServiceResult.Success-> activityCoreDataConverter.convert(response.data)
           is DataServiceResult.Error-> throw KotlinNullPointerException(response.exception.localizedMessage)

           }
    }

    suspend fun getActivities(numOfActivities: Int): MutableList<ActivityCoreData> {

        var listOfActivities = mutableListOf<ActivityCoreData>()

        for (i in 0..numOfActivities) {
            when (val response = dataService.requestApiDataToObject<ActivityResponse>("https://www.boredapi.com/api/activity/")) {
                is DataServiceResult.Success -> listOfActivities.add(activityCoreDataConverter.convert(response.data))
                is DataServiceResult.Error -> throw KotlinNullPointerException(response.exception.localizedMessage)

            }
        }
        return listOfActivities
    }

}