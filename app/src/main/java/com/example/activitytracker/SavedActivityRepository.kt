package com.example.activitytracker

import android.util.Log
import com.example.activitytracker.services.SharedPreferencesService

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

