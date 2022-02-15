package com.example.activitytracker.ui.main.activity

import androidx.lifecycle.ViewModel
import com.example.activitytracker.services.activity.SavedActivityRepository
import com.example.activitytracker.models.ActivityCoreData
import com.example.activitytracker.services.json.JsonService

class ActivityDetailsViewModel(
    private val savedActivityRepository: SavedActivityRepository,
    private val jsonService: JsonService
) : ViewModel() {
    lateinit var activityDataString: String
    lateinit var activityCoreData: ActivityCoreData

    fun convertDataStringToActivity(activityString: String){
        activityCoreData = jsonService.convertToObject(activityString)
    }

    fun unfollowActivity(activityData: ActivityCoreData){
        savedActivityRepository.unfollowActivity(activityData.activityId)
        activityData.activityFollowed = false
    }

    fun followActivity(activityData: ActivityCoreData){
        savedActivityRepository.followActivity(activityData.activityId, activityData.activityProgress)
        activityData.activityFollowed = true
    }

    fun updateActivityProgress(progress: Int?){
        if(progress != null){
            activityCoreData.activityProgress = progress
            savedActivityRepository.updateActivityProgress(activityCoreData.activityId, activityCoreData.activityProgress)

        }
    }



}