package com.example.activitytracker

import com.example.activitytracker.services.data.SharedPreferencesService

class SavedActivityRepository(
    private val sharedPreferencesService: SharedPreferencesService
) {
    private val fileName = "SavedActivities"

    init {
        sharedPreferencesService.openSharedPreferences(fileName)
    }
     fun getSavedActivites(): List<String>{
        val savedActivities = sharedPreferencesService.getSharedPreferenceKeys()

        return when{
            savedActivities.isNullOrEmpty() -> emptyList()
            else -> savedActivities.toList()
        }
    }

    fun updatedActivityProgress(activityKey: String, activityProgress: Int){
        sharedPreferencesService.saveToSharedPreferences(activityKey,  activityProgress)
    }

    fun followActivity(activityKey: String, activityProgress: Int){
        sharedPreferencesService.saveToSharedPreferences(activityKey, activityProgress)
    }

    fun unfollowActivity(activityKey: String){
        sharedPreferencesService.removeFromSharedPreferences(activityKey)
    }




}

