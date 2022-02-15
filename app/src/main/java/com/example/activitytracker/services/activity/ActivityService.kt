package com.example.activitytracker.services.activity

import com.example.activitytracker.models.ActivityCoreData
import com.example.activitytracker.models.ActivityQueryData
import com.example.activitytracker.models.ActivityResponse
import com.example.activitytracker.services.converter.ActivityResponseToActivityCoreDataConverter
import com.example.activitytracker.services.data.DataService
import com.example.activitytracker.services.results.DataServiceResult
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class ActivityService(
    private val dataService: DataService,
    private val activityCoreDataConverter: ActivityResponseToActivityCoreDataConverter
    ) {

    companion object{
        const val activityRequestUrl = "https://www.boredapi.com/api/activity/"
        const val activityKeyRequestUrl = "https://www.boredapi.com/api/activity?key="
    }

    suspend fun getRandomSingleActivity(savedActivityKeys: List<String>): ActivityCoreData{
        return when(val response = dataService.requestApiDataToObject<ActivityResponse>(
            activityRequestUrl
        )){
           is DataServiceResult.Success-> {
               val activityToAdd = activityCoreDataConverter.convert(response.data)
               activityToAdd.activityFollowed = savedActivityKeys.contains(activityToAdd.activityId)
               activityToAdd
           }
           is DataServiceResult.Error-> throw KotlinNullPointerException(response.exception.localizedMessage)
           }
    }

    suspend fun getSingleActivity(key: String): ActivityCoreData{
        return when(val response = dataService.requestApiDataToObject<ActivityResponse>(
            activityKeyRequestUrl +key)){
            is DataServiceResult.Success-> activityCoreDataConverter.convert(response.data)
            is DataServiceResult.Error-> throw KotlinNullPointerException(response.exception.localizedMessage)
        }
    }

    //returns a list of n random converted activityCoreData objects for use in the consuming fragment
    suspend fun getRandomActivities(numOfActivities: Int): MutableList<ActivityCoreData> {
        val listOfActivities = mutableListOf<ActivityCoreData>()

        for (i in 0..numOfActivities) {
            when (val response = dataService.requestApiDataToObject<ActivityResponse>(
                activityRequestUrl
            )) {
                is DataServiceResult.Success -> listOfActivities.add(activityCoreDataConverter.convert(response.data))
                is DataServiceResult.Error -> throw KotlinNullPointerException(response.exception.localizedMessage)
            }
        }
        return listOfActivities
    }
    //returns a list of converted activityCoreData objects for use in the consuming fragment
    suspend fun getSavedActivities(activityData: Map<String,*>): MutableList<ActivityCoreData> {
        val listOfActivities = mutableListOf<ActivityCoreData>()

        for (item in activityData) {
            when (val response = dataService.requestApiDataToObject<ActivityResponse>(activityKeyRequestUrl + item.key)) {
                is DataServiceResult.Success -> {
                    val activityToAdd = activityCoreDataConverter.convert(response.data)
                    activityToAdd.activityFollowed = true
                    activityToAdd.activityProgress = item.value as Int
                    listOfActivities.add(activityToAdd)
                }
                is DataServiceResult.Error -> throw KotlinNullPointerException(response.exception.localizedMessage)
            }
        }
        return listOfActivities
    }

    //For search menu allows to add parameter queries onto api string
    suspend fun getActivitiesWithParameters(numOfActivities: Int, queryData: ActivityQueryData, savedActivityKeys: List<String>): MutableList<ActivityCoreData> {
        val listOfActivities = mutableListOf<ActivityCoreData>()

        for (i in 0..numOfActivities) {
            when (val response = dataService.requestApiDataToObject<ActivityResponse>(
                ActivityQueryBuilder().buildQuery(queryData))) {
                is DataServiceResult.Success -> {
                    val activityToAdd = activityCoreDataConverter.convert(response.data)
                    activityToAdd.activityFollowed = savedActivityKeys.contains(activityToAdd.activityId)
                    if(!listOfActivities.contains(activityToAdd)){
                        listOfActivities.add(activityToAdd)
                    }
                }
                is DataServiceResult.Error ->  emptyList<ActivityCoreData>()
            }
        }
        return listOfActivities
    }
}