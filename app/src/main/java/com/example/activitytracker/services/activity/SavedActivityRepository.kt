package com.example.activitytracker.services.activity

import com.example.activitytracker.services.data.SharedPreferencesService

class SavedActivityRepository(
    private val sharedPreferencesService: SharedPreferencesService
) {
    private val fileName = "SavedActivities"

    init {
        sharedPreferencesService.openSharedPreferences(fileName)
    }
     fun getSavedActivites(): Map<String, *>{
        val savedActivities = sharedPreferencesService.getSharedPreferences()
        return when{
            savedActivities.isNullOrEmpty() -> emptyMap<String,Int>()
            else -> savedActivities
        }
    }
    fun getSavedActivitesKeys(): List<String>{
        val savedActivities = sharedPreferencesService.getSharedPreferenceKeys()

        return when{
            savedActivities.isNullOrEmpty() -> emptyList()
            else -> savedActivities.toList()
        }
    }

    fun updateActivityProgress(activityKey: String, activityProgress: Int){
        sharedPreferencesService.updateSharedPreference(activityKey,  activityProgress)
    }

    fun followActivity(activityKey: String, activityProgress: Int){
        sharedPreferencesService.saveToSharedPreferences(activityKey, activityProgress)
    }

    fun unfollowActivity(activityKey: String){
        sharedPreferencesService.removeFromSharedPreferences(activityKey)
    }




}

